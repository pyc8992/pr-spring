package domain.utils.excel

import domain.utils.excel.annotation.ExcelColumn
import domain.utils.excel.annotation.ExcelDocument
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.usermodel.CellType.*
import java.lang.Exception
import java.lang.reflect.Field
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.stream.StreamSupport
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.streams.toList

abstract class AbstractExcelParser<T : Any>(
  private val targetClass: KClass<T>,
  private val ignoreUnknownObjectHeader: Boolean = false,
  private val ignoreUnknownExcelHeader: Boolean = false
) : ExcelParser<T> {
  var sheetIndex: Int = 0
  var minRowLimit: Int = 0
  var maxRowLimit: Int = 0
  var headerIndex: Int = 0
  var rowStartIndex: Int = 0

  init {
    val excelDocument = (targetClass.findAnnotation<ExcelDocument>()
      ?: throw IllegalArgumentException("Unsupported Class : ${targetClass.simpleName}"))

    sheetIndex = excelDocument.sheetIndex
    minRowLimit = excelDocument.minRowLimit
    maxRowLimit = excelDocument.maxRowLimit
    headerIndex = excelDocument.headerIndex
    rowStartIndex = excelDocument.rowStartIndex
  }

  override fun getSheet(bytes: ByteArray): Sheet = ExcelReader.read(bytes, sheetIndex)

  override fun getActualRows(sheet: Sheet): List<Row> = StreamSupport.stream(sheet.spliterator(), false)
    .filter { it.rowNum >= rowStartIndex && it.any { cell -> cell.cellType != BLANK } }.toList()

  override fun getHeaders(sheet: Sheet): Row = sheet.getRow(headerIndex)

  override fun validate(rows: List<Row>) {
    validateRowLimit(rows.size)
  }

  override fun convert(source: List<Row>, headerMap: HashMap<String, Int>): List<T> {
    TODO("Not yet implemented")
  }

  private fun convert(source: Row, headersMap: HashMap<String, Int>): T = targetClass.java.declaredFields
    .fold(targetClass.createInstance()) { acc: T, field: Field ->
      val annotation = getAnnotation(field) ?: return acc

      val headerIndex = (headersMap[annotation.columnName]
        ?: if (ignoreUnknownObjectHeader) {
          return acc
        } else {
          throw IllegalArgumentException("Unkown header : ${annotation.columnName}")
        })

      val cell = source.getCell(headerIndex)

      if ((cell == null || cell.cellType == BLANK) && !annotation.nullable) {
        throw IllegalArgumentException("${annotation.columnName} is not nullable")
      }

      setField(acc, field, cell)
      acc
    }

  protected fun setField(instance: T, field: Field, cell: Cell?) {
    field.isAccessible = true
    field.set(instance, cell?.let { parse(field, cell) })
  }

  protected fun parse(field: Field, cell: Cell): Any? {
    when (cell.cellType) {
      STRING -> {
        if (field.type == java.lang.String::class.java || field.type == String::class.java) {
          return cell.stringCellValue
        }

        return stringNumberExtract(field, cell.stringCellValue.trim())
      }
      NUMERIC -> {
        if (field.type == Int::class.java) {
          return cell.numericCellValue.toInt()
        }

        if (field.type == Long::class.java) {
          return cell.numericCellValue.toLong()
        }

        if (field.type == Float::class.java) {
          return cell.numericCellValue.toFloat()
        }

        if (field.type == Double::class.java) {
          return cell.numericCellValue
        }

        if (field.type == LocalDate::class.java && DateUtil.isCellDateFormatted(cell)) {
          return cell.localDateTimeCellValue.toLocalDate()
        }

        if (field.type == LocalDateTime::class.java && DateUtil.isCellDateFormatted(cell)) {
          return cell.localDateTimeCellValue
        }

        if (field.type == LocalTime::class.java && DateUtil.isCellDateFormatted(cell)) {
          return cell.localDateTimeCellValue.toLocalTime()
        }
      }
      BOOLEAN -> {
        if (field.type == Boolean::class.java) {
          return cell.booleanCellValue
        }
      }
      BLANK -> return null
      else -> throw IllegalArgumentException("'${field.name}' is not supported type, [cell type]: ${cell.cellType}")
    }

    throw IllegalArgumentException("'${field.name}' is invalid type, [cell type]: ${cell.cellType}, [field type]: ${field.type}")
  }

  private fun stringNumberExtract(field: Field, value: String): Any = try {
    when (field.type) {
      java.lang.Integer::class.java, Int::class.java -> value.toInt()
      java.lang.Long::class.java, Long::class.java -> value.toLong()
      Float::class.java -> value.toFloat()
      Double::class.java -> value.toDouble()
      else -> throw IllegalArgumentException()
    }
  } catch (e: Exception) {
    throw IllegalArgumentException("'${field.name}' is cannot get value from string, [field type]: ${field.type}, [cell value]: $value")
  }

  override fun makeIndexMapByHeaderName(headers: Row): HashMap<String, Int> {
    val headersMap = hashMapOf<String, Int>()
    val sourceHeaders = headers.map { it.stringCellValue }

    val targetHeaders = getTargetFields()

    if (!ignoreUnknownObjectHeader && !ignoreUnknownExcelHeader) {
      if (sourceHeaders.size != targetHeaders.size) {
        throw IllegalArgumentException("Header count not matched [ expected : ${targetHeaders.size}, actual : ${sourceHeaders.size}]")
      }
    }

    sourceHeaders.forEachIndexed { index, sourceHeader ->
      when (ignoreUnknownExcelHeader) {
        true -> {
          if (sourceHeader in targetHeaders) {
            headersMap[sourceHeader] = index
          }
        }
        false -> {
          if (sourceHeader != targetHeaders[index]) {
            throw IllegalArgumentException("Header not matched [ expected : ${targetHeaders[index]}, actual : ${sourceHeader}]")
          }
          headersMap[sourceHeader] = index
        }
      }
    }

    return headersMap
  }

  protected fun validateRowLimit(rowCount: Int) {
    if (rowCount < minRowLimit || rowCount > maxRowLimit) {
      throw IllegalArgumentException("greater or less then row limit [ minimum : $minRowLimit, maximum : $maxRowLimit, actual : $rowCount")
    }
  }

  protected fun getTargetFields() = targetClass.java.declaredFields.mapNotNull {
    getAnnotation(it)?.columnName
  }

  protected fun getAnnotation(field: Field): ExcelColumn? {
    val annotation = field.getAnnotation(ExcelColumn::class.java)

    return when (ignoreUnknownObjectHeader) {
      true -> annotation
      false -> annotation ?: throw IllegalArgumentException("Unsupported type : ${field.name}")
    }
  }

}

package domain.utils.excel

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

interface ExcelParser<T> {
  fun get(bytes: ByteArray): List<T> {
    val sheet: Sheet = getSheet(bytes)

    val actualRows = getActualRows(sheet)

    validate(actualRows)

    val headerMap = makeIndexMapByHeaderName(getHeaders(sheet))

    return convert(actualRows, headerMap)
  }

  fun getSheet(bytes: ByteArray): Sheet

  fun getActualRows(sheet: Sheet): List<Row>

  fun getHeaders(sheet: Sheet): Row

  fun validate(rows: List<Row>)

  fun convert(source: List<Row>, headerMap: HashMap<String, Int>): List<T>

  fun makeIndexMapByHeaderName(headers: Row): HashMap<String, Int>
}

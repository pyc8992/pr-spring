package domain.utils.excel

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.ByteArrayInputStream

object ExcelReader {
  fun read(bytes: ByteArray, sheetIndex: Int): Sheet {
    val bin = ByteArrayInputStream(bytes)
    val workbook = WorkbookFactory.create(bin)
    return workbook.getSheetAt(sheetIndex)
  }
}

package domain.utils.excel.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExcelDocument(
  val sheetIndex: Int = 0,
  val minRowLimit: Int = 0,
  val maxRowLimit: Int = 10000,
  val headerIndex: Int = 0,
  val rowStartIndex: Int = 1
)

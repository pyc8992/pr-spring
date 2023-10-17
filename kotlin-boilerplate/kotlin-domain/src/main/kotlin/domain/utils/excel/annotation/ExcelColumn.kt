package domain.utils.excel.annotation

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, PROPERTY)
@Retention(RUNTIME)
@MustBeDocumented
annotation class ExcelColumn(
  val columnName: String,
  val order: Int,
  val nullable: Boolean = true
)

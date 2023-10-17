package domain.utils.excel

import kotlin.reflect.KClass

class DefaultExcelParser<T : Any>(
  targetClass: KClass<T>,
  ignoreUnknownHeader: Boolean = false
) : AbstractExcelParser<T>(targetClass, ignoreUnknownHeader)

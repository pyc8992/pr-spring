package domain.dao.test.condition

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import domain.dao.test.QFoo.foo

class FooCondition private constructor (
  val bar1: String?,
  val bar2: String?
) {
  companion object {
    fun of(bar1: String?, bar2: String?) : FooCondition {
      return FooCondition(bar1 = bar1, bar2 = bar2)
    }
  }

  fun build(): Predicate? {
    val builder = BooleanBuilder()
    this.bar1?.let { builder.and(foo.bar1.eq(this.bar1)) }
    this.bar2?.let { builder.and(foo.bar1.eq(this.bar2)) }

    return builder
  }

}

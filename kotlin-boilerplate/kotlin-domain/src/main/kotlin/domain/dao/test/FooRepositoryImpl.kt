package domain.dao.test

import domain.dao.test.QFoo.foo
import domain.dao.test.condition.FooCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class FooRepositoryImpl : QuerydslRepositorySupport(Foo::class.java), FooRepositoryCustom {
  override fun findByCondition(condition: FooCondition, pageable: Pageable): Page<Foo> {

    val fetchResults = from(foo)
      .where(condition.build())
      .limit(pageable.pageSize.toLong())
      .offset(pageable.offset)
      .orderBy(foo.id.asc())
      .fetchResults()

    return PageImpl(fetchResults.results, pageable, fetchResults.total)
  }
}

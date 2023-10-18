package domain.dao.test

import domain.dao.test.condition.FooCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FooRepositoryCustom {
  fun findByCondition(condition: FooCondition, pageable: Pageable): Page<Foo>
}

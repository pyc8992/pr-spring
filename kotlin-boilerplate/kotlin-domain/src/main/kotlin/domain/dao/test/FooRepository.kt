package domain.dao.test

import org.springframework.data.repository.CrudRepository

interface FooRepository : CrudRepository<Foo, Long>, FooRepositoryCustom {
  fun findByBar1(bar1: String) : List<Foo>
}

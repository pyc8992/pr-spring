package domain.service

import domain.dao.test.FooRepository
import domain.dao.test.condition.FooCondition
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HelloServiceImpl(
  private val fooRepository: FooRepository
) : HelloService {
  override fun hello(name: String): String {
    val result = fooRepository.findByBar1(name)

    if (result.isEmpty()) {
      throw IllegalArgumentException("not found")
    }

    return "hello = ${result.first().bar2}"
  }

  override fun helloByCondition(name: String, page: Int, size: Int): String {
    val result = fooRepository.findByCondition(FooCondition.of(name, null), PageRequest.of(page - 1, size))

    if (result.content.isEmpty()) {
      throw IllegalArgumentException("not found")
    }

    return "hello = ${result.first().bar2}"
  }
}

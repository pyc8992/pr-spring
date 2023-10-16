package domain.service

import domain.dao.test.FooRepository
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
}

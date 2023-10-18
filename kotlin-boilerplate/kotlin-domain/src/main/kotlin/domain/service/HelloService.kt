package domain.service

interface HelloService {
  fun hello(name: String): String

  fun helloByCondition(name: String, page: Int, size: Int): String
}

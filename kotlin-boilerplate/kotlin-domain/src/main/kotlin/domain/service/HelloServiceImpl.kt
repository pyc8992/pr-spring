package domain.service

import org.springframework.stereotype.Service

@Service
class HelloServiceImpl : HelloService {
  override fun hello(name: String): String {
    return "hello = $name"
  }
}

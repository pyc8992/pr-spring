package api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import domain.service.HelloService
import org.springframework.web.bind.annotation.RequestParam

@RestController
class HelloController(
  private val helloService: HelloService
) {
  @GetMapping("/hello")
  fun hello(@RequestParam name: String) = helloService.hello(name)
}

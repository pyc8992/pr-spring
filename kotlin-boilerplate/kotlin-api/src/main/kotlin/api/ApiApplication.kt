package api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ApiApplication

fun main(vararg args: String) {
  runApplication<ApiApplication>(*args)
}


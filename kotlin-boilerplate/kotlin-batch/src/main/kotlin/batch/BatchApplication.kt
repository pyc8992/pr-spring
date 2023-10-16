package batch

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
open class BatchApplication

fun main(vararg args: String) {
  val applicationContext = runApplication<BatchApplication>(
    args = *args,
    init = {
      webApplicationType = WebApplicationType.NONE
    }
  )

  val exitCode = SpringApplication.exit(applicationContext)

  exitProcess(exitCode)
}

package batch.jobs

import batch.infra.tasklet.AbstractTasklet
import domain.service.HelloService
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class HelloJobTasklet(
  private val helloService: HelloService
) : AbstractTasklet() {

  override fun execute(): RepeatStatus {
    val hello = helloService.hello("update1")
    println(hello)
    return RepeatStatus.FINISHED
  }
}

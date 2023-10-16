package batch.infra.tasklet

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

abstract class AbstractTasklet : Tasklet {
  lateinit var parameterMap: Map<String, String>

  override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
    this.parameterMap = chunkContext.stepContext.stepExecution.jobParameters.parameters.mapValues { entry -> entry.value.toString() }

    return execute()
  }

  abstract fun execute(): RepeatStatus

  fun getParameter(key: String) = parameterMap[key]

  fun getParameter(key: String, defaultValue: String): String {
    val parameter = getParameter(key)
    if (parameter.isNullOrBlank()) {
      return defaultValue
    }

    return parameter
  }

  inline fun <reified T : Enum<T>> getEnumParameter(key: String): T? {
    val parameter = getParameter(key)
    if (parameter.isNullOrBlank()) {
      return null
    }
    return enumValues<T>().firstOrNull { it.name == parameter }
  }
}

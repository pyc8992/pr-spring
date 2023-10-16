package batch.infra

import batch.infra.incrementer.ParamCleanRunIdIncrementer
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.support.transaction.ResourcelessTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BaseJobConfiguration(
  protected val jobBuilderFactory: JobBuilderFactory,
  protected val stepBuilderFactory: StepBuilderFactory
) {

  @Bean
  open fun resourcelessTransactionManager(): ResourcelessTransactionManager = ResourcelessTransactionManager()

  fun jobBuilder(jobName: String) = jobBuilderFactory.get(jobName).incrementer(ParamCleanRunIdIncrementer())

  fun stepBuilder(stepName: String) = stepBuilderFactory.get(stepName).transactionManager(resourcelessTransactionManager())
}

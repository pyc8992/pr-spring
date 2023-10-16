package batch.jobs

import batch.infra.BaseJobConfiguration
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["spring.batch.job.names"], havingValue = "helloJob")
open class HelloJobConfiguration(
  jobBuilderFactory: JobBuilderFactory,
  stepBuilderFactory: StepBuilderFactory,
  val helloJobTasklet: HelloJobTasklet
) : BaseJobConfiguration(jobBuilderFactory, stepBuilderFactory) {

  @Bean
  open fun helloJob(): Job = jobBuilder("helloJob").start(helloJobStep()).build()

  @Bean
  open fun helloJobStep(): Step = stepBuilder("helloJobStep").tasklet(helloJobTasklet).build()
}

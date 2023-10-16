package batch.configuration

import domain.configuration.DomainConfiguration
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
@Import(DomainConfiguration::class)
open class BatchConfiguration {

  @Value("\${spring.batch.job.names:NONE}")
  lateinit var jobName: String

  @PostConstruct
  fun validateJobNames() {
    if ("NONE" == jobName) {
      throw IllegalArgumentException("Invaild jobNames! examples: spring.batch.job.names=job1,job2...")
    }
  }

  @Bean
  open fun batchConfigurer() = object : DefaultBatchConfigurer() {
    override fun setDataSource(dataSource: DataSource) {
    }
  }
}

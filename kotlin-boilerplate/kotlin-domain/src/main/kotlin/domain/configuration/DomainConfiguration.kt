package domain.configuration

import domain.configuration.datasource.test.TestDataSourceConfiguration
import domain.service.Service
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
  TestDataSourceConfiguration::class
)
@ComponentScan(basePackageClasses = [Service::class])
open class DomainConfiguration

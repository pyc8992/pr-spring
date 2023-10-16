package domain.configuration

import domain.service.Service
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [Service::class])
open class DomainConfiguration

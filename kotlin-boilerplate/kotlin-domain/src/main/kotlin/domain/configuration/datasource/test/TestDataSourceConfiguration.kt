package domain.configuration.datasource.test

import com.zaxxer.hikari.HikariDataSource
import domain.dao.test.TestDao
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource

@Configuration
@EntityScan(basePackageClasses = [TestDao::class])
@EnableJpaRepositories(basePackageClasses = [TestDao::class])
open class TestDataSourceConfiguration {

  @Bean
  @Primary
  open fun dataSourceProperties(): DataSourceProperties {
    return DataSourceProperties()
  }

  @Bean
  @Primary
  open fun dataSource(@Qualifier("dataSourceProperties") dataSourceProperties: DataSourceProperties): HikariDataSource {
    return dataSourceProperties.initializeDataSourceBuilder()
      .type(HikariDataSource::class.java)
      .build()
  }

  @Bean
  @Primary
  open fun entityManagerFactory(@Qualifier("dataSource") dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
    val factory = LocalContainerEntityManagerFactoryBean()
    factory.setPackagesToScan(TestDao::class.java.`package`.name)
    factory.dataSource = dataSource
    factory.persistenceUnitName = "test"
    factory.jpaVendorAdapter = HibernateJpaVendorAdapter()
    return factory
  }

  @Bean
  @Primary
  open fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: LocalContainerEntityManagerFactoryBean
  ) : PlatformTransactionManager {
    return JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.`object`)!!)
  }
}

package domain.configuration.elasticsearch;

import domain.dao.es.EsDao;
import domain.infra.es.configuration.ElasticSearchDataSource;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ComponentScan(basePackageClasses = EsDao.class)
@EnableConfigurationProperties
public class EsDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "domain.datasource.elasticsearch")
    public ElasticSearchDataSource elasticSearchDataSource() {
        return new ElasticSearchDataSource();
    }

    @Bean
    @DependsOn("elasticSearchDataSource")
    public RestHighLevelClient restHighLevelClient() {
        HttpHost[] httpHosts = elasticSearchDataSource().getHosts().stream()
                .map(it -> new HttpHost(it.getHost(), it.getPort()))
                .toArray(HttpHost[]::new);

        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}

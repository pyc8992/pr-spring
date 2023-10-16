package api.configuration;

import domain.configuration.DomainConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    DomainConfiguration.class
})
public class ApiConfiguration {
}

package hello.spring_start.config;

import hello.spring_start.config.EnableMyAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan(basePackages = {"hello.spring_start", "hello.spring_start.service", "hello.spring_start.controller"})
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}

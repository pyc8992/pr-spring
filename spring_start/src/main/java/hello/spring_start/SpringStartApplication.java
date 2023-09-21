package hello.spring_start;

import hello.spring_start.controller.HelloController;
import hello.spring_start.controller.HelloRestController;
import hello.spring_start.service.HelloService;
import hello.spring_start.service.SimpleHelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"hello.spring_start.service", "hello.spring_start.controller"})
public class SpringStartApplication {

//	@Bean
//	public HelloRestController helloRestController(HelloService helloService) {
//		return new HelloRestController(helloService);
//	}
//
//	@Bean
//	public HelloService helloService() {
//		return new SimpleHelloService();
//	}

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
//        MySpringApplication.run(SpringStartApplication.class, args);
		SpringApplication.run(SpringStartApplication.class, args);
    }
}

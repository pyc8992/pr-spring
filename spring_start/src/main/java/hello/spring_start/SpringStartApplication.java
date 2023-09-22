package hello.spring_start;

import hello.spring_start.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
@MySpringBootApplication
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

    public static void main(String[] args) {
//        MySpringApplication.run(SpringStartApplication.class, args);
		SpringApplication.run(SpringStartApplication.class, args);
    }
}

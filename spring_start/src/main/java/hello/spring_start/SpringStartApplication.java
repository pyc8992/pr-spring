package hello.spring_start;

import hello.spring_start.config.MySpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@SpringBootApplication
@MySpringBootApplication
public class SpringStartApplication {

//    @Bean
//    ApplicationRunner applicationRunner(Environment env) {
//        return args -> {
//            String name = env.getProperty("my.name");
//            System.out.println("name = " + name);
//        };
//    }

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

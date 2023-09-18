package hello.spring_start.controller;

import hello.spring_start.service.SimpleHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//@RestController
public class HelloRestController {
//    @GetMapping("/v2/hello")
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}

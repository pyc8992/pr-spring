package hello.spring_start.controller;

import hello.spring_start.service.HelloService;

import java.util.Objects;

//@RestController
public class HelloRestController {
    private final HelloService helloService;

    public HelloRestController(HelloService helloService) {
        this.helloService = helloService;
    }

    //    @GetMapping("/v2/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}

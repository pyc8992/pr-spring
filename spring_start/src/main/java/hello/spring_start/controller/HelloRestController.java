package hello.spring_start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/v2/hello")
    public String hello(String name) {
        return "Hello " + name;
    }
}

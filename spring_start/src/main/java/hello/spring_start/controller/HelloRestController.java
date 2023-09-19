package hello.spring_start.controller;

import hello.spring_start.MyComponent;
import hello.spring_start.service.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//@RestController
//@RequestMapping("/hello")
@RestController // @ResponseBody, @RequestMapping 생략 가능
public class HelloRestController
//        implements ApplicationContextAware
{
    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public HelloRestController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("applicationContext = " + applicationContext);
//        this.applicationContext = applicationContext;
//    }
}

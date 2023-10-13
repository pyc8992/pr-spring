package domain.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "welcome " + name;
    }
}

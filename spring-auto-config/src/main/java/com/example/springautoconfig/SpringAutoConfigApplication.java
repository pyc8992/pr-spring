package com.example.springautoconfig;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAutoConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAutoConfigApplication.class, args);
    }

}

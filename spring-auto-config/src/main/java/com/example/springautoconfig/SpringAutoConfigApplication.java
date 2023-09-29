package com.example.springautoconfig;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAutoConfigApplication {

    @Bean
    ApplicationRunner run(ConditionEvaluationReport report) {
        return args -> System.out.println(
                report.getConditionAndOutcomesBySource().entrySet()
                        .stream()
                        .filter(co -> co.getValue().isFullMatch())
                        .filter(co -> !co.getKey().contains("Jmx"))
                        .peek(co -> {
                            System.out.println(co.getKey());
                            co.getValue().forEach(c -> {
                                System.out.println("\t" + c.getOutcome());
                            });
                            System.out.println("\n");
                        }).count()
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAutoConfigApplication.class, args);
    }

}

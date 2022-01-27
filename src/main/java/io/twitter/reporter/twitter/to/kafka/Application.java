package io.twitter.reporter.twitter.to.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.twitter.reporter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package io.twitter.reporter.twifka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "io.twitter.reporter")
public class TwifkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwifkaApplication.class, args);
    }
}

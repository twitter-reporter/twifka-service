package io.twitter.reporter.twitter.to.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties("twitter.to.kafka")
public class TwitterToKafkaConfiguration {

    private Set<String> keywords;

}

package io.twitter.reporter.twitter.to.kafka;

import io.twitter.reporter.twitter.to.kafka.service.TwitterStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamRunner implements CommandLineRunner {

    private final TwitterStreamService twitterStreamService;

    @Override
    public void run(String... args) {
        twitterStreamService.start();
    }
}

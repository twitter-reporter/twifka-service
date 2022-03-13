package io.twitter.reporter.twifka;

import io.twitter.reporter.twifka.service.TwitterStreamService;
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

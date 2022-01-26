package io.twitter.reporter.twitter.to.kafka.service;

import io.twitter.reporter.twitter.to.kafka.config.TwitterToKafkaConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.StreamListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class TwitterStreamService {

    private final TwitterStream twitterStream;

    private final TwitterToKafkaConfiguration configuration;

    public TwitterStreamService(final TwitterToKafkaConfiguration configuration,
                                final StreamListener streamListener) {
        this.twitterStream = new TwitterStreamFactory().getInstance();
        this.twitterStream.addListener(streamListener);
        this.configuration = configuration;
    }

    public void start() {
        final String[] keywords = configuration.getKeywords().toArray(new String[0]);
        log.info("Filtering stream with keywords: [{}]", keywords);
        final FilterQuery filterQuery = new FilterQuery(keywords);

        twitterStream.filter(filterQuery);
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            log.info("Shutting down the Twitter stream...");
            twitterStream.shutdown();
        }
    }
}

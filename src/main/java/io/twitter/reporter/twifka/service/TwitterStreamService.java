package io.twitter.reporter.twifka.service;

import io.twitter.reporter.config.TwitterToKafkaConfiguration;
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

    private final TwitterToKafkaConfiguration twitterToKafkaConfiguration;

    public TwitterStreamService(final TwitterToKafkaConfiguration twitterToKafkaConfiguration,
                                final StreamListener streamListener) {
        this.twitterStream = new TwitterStreamFactory().getInstance();
        this.twitterStream.addListener(streamListener);
        this.twitterToKafkaConfiguration = twitterToKafkaConfiguration;
    }

    public void start() {
        final FilterQuery filterQuery = addFilter();

        twitterStream.filter(filterQuery);
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            log.info("Shutting down the Twitter stream...");
            twitterStream.shutdown();
        }
    }

    private FilterQuery addFilter() {
        final String[] keywords = twitterToKafkaConfiguration.getTwitterKeywords().toArray(new String[0]);
        log.info("Filtering stream with keywords: [{}]", keywords);
        return new FilterQuery(keywords);
    }
}

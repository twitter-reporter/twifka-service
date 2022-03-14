package io.twitter.reporter.twifka.service;

import io.twitter.reporter.config.TwifkaConfiguration;
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

    private final TwifkaConfiguration twifkaConfiguration;

    public TwitterStreamService(final TwifkaConfiguration twifkaConfiguration,
                                final StreamListener streamListener) {
        this.twitterStream = new TwitterStreamFactory().getInstance();
        this.twitterStream.addListener(streamListener);
        this.twifkaConfiguration = twifkaConfiguration;
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
        final String[] keywords = twifkaConfiguration.getTwitterKeywords().toArray(new String[0]);
        log.info("Filtering stream with keywords: [{}]", keywords);

        return new FilterQuery(keywords);
    }
}

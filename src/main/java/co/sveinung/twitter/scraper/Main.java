package co.sveinung.twitter.scraper;

import twitter4j.*;

/**
 * Created by sveinung on 6/12/15.
 */
public class Main {

    private static final String CONSUMER_KEY = "your-key";
    private static final String CONSUMER_SECRET = "your-secret";

    public static void main(String[] args) {
        try {
            final TwitterScraper twitterScraper = new TwitterScraper(CONSUMER_KEY, CONSUMER_SECRET, new ImageProcessor(args[1]));
            twitterScraper.search(args[0]);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}


package com.usebutton.twitter.scraper;

import twitter4j.*;

/**
 * Created by sveinung on 6/12/15.
 */
public class Main {

    private static final String CONSUMER_KEY = "HutP5WjCRt1uZtpgvmmfmA";
    private static final String CONSUMER_SECRET = "NozCosLlp9xF2CV0DbQgoUgSGqXN4SWt8NxPcW4H08";

    public static void main(String[] args) {
        try {
            final TwitterScraper twitterScraper = new TwitterScraper(CONSUMER_KEY, CONSUMER_SECRET, new ImageProcessor(args[1]));
            twitterScraper.search(args[0]);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}


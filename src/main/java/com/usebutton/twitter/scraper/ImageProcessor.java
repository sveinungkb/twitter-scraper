package com.usebutton.twitter.scraper;

import twitter4j.MediaEntity;
import twitter4j.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sveinung on 6/12/15.
 */
public class ImageProcessor implements TwitterScraper.Processor {
    private final File output;
    private final static Pattern fileExtensionPattern = Pattern.compile(".*(\\.\\w+)");
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE-HH-mm-ss");

    public ImageProcessor(final String destination) {
        this.output = new File(destination);
        output.mkdirs();
    }

    @Override
    public void processTweet(Status tweet) {
        if (tweet.getMediaEntities() == null) return;
        for (MediaEntity entity : tweet.getMediaEntities()) {
            processMedia(tweet, entity);
        }
    }
        private void processMedia(Status tweet, MediaEntity entity){
            final String url = entity.getMediaURL();
            final String fileName = dateFormatter.format(tweet.getCreatedAt()) + "-@" + tweet.getUser().getScreenName() + fileExtensionFor(url);
            final File out = new File(output, fileName);
            System.out.println("Downloading: " + url + " to " + out.getAbsolutePath());

            try {
                URL imageUrl = new URL(url);
                ReadableByteChannel rbc = Channels.newChannel(imageUrl.openStream());
                FileOutputStream fos = new FileOutputStream(out);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (Exception e) {
                System.err.println("Could not download: " + url);
                e.printStackTrace();
            }
        }

    private String fileExtensionFor(String url) {
        final Matcher matcher = fileExtensionPattern.matcher(url);
        if (matcher.matches()) {
            return matcher.group(matcher.groupCount());
        }
        return ".png";
    }
}

## Twitter Image Scraper

Quick console app using [Twitter4J](http://twitter4j.org)'s to query Twitter for tweets matching a query and downloading all media entities (photos) to disk.

Disclaimer: Do not use against Twitter's API policies, I'm sure they have some on this.

Usage:

1. Change consumer key and consumer secret in `Main.java`.
2. `gradle assembleRelease`
3. `java -jar build/libs/twitter-scraper-1.0.jar "#somehashtag" "pathToOutput"` 

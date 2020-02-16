# Crawler for getting Top JS Libraries from Google search Results

### Intro:
This application crawls through webpages from Google Search Results for a given query and prints Top Javascript libraries used in those webpages

### Set-up and run application
1. Goto `crawler` directory
2. Do `mvn package`
3. Goto `target` folder in `crawler`
4. Do `java -jar crawler-1.0-SNAPSHOT-jar-with-dependencies.jar `
5. Put in your search query
6. Top five JavaScript Libraries will be displayed on the terminal in increasing order of their occurence count.


### Third Party Libraries Used
1. Jsoup for fetching and parsing webpages
2. Log4j for logging


### Sample STDIN - STDOUT

 Input keyword for Google Search

`Harry Potter`

```
Top 5 Javascript Libraries Used are:

[vendor.82c8a684eae5bb428f8c.js, ag.core.js, manifest.512ebda9bfa59595b45b.js, loader.js, flbuttons.min.js]
```


Input keyword for Google Search

`Vikas Sangle`

```
0    [pool-1-thread-5] ERROR crawler.Crawler  - Could Not Connect to URL: https://www.instagram.com/lostboyjourney/%3Fhl%3Den due to: HTTP error fetching URL
867  [pool-1-thread-1] ERROR crawler.Crawler  - Could Not Connect to URL: https://twitter.com/lostboy54%3Flang%3Den due to: HTTP error fetching URL
2104 [pool-1-thread-5] ERROR crawler.Crawler  - Could Not Connect to URL: https://www.youtube.com/watch%3Fv%3DGLdq1BruoVw due to: HTTP error fetching URL
2221 [pool-1-thread-2] ERROR crawler.Crawler  - Could Not Connect to URL: https://www.youtube.com/watch%3Fv%3D8jwTj9TJq74 due to: HTTP error fetching URL
2298 [pool-1-thread-1] ERROR crawler.Crawler  - Could Not Connect to URL: https://www.youtube.com/watch%3Fv%3DGLdq1BruoVw due to: HTTP error fetching URL
2346 [pool-1-thread-3] ERROR crawler.Crawler  - Could Not Connect to URL: https://www.youtube.com/watch%3Fv%3D8jwTj9TJq74 due to: HTTP error fetching URL

Top 5 Javascript Libraries Used are:
[jquery.min.js, conversion.js, jquery.bxslider.js, widgets.js, gpt.js]
```

package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;
import util.ParserUtil;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

public class Crawler {
    private static final Logger logger = LoggerFactory.getLogger(Crawler.class);

    public static Document getWebpage(String url) {
        Document document = new Document("");
        try {
            document = Jsoup
                    .connect(url)
                    .userAgent("Mozilla")
                    .timeout(Constants.CONNECTION_TIMEOUT)
                    .get();
        } catch (HTTPException e) {
            //Catching these exceptions to not fail in-case any of the webpages do not respond in time
            logger.error("Could Not Connect to URL: {} due to: {}" + e.getMessage());
        } catch (IOException e) {
            logger.error("Could Not Connect to URL: {} due to: {}", url, e.getMessage());
        }
        return document;
    }

    public class AsyncCrawler implements Runnable{

        private BlockingDeque<String> urlQueue;

        private ConcurrentHashMap<String, Integer> libraryCountMap;

        public AsyncCrawler(BlockingDeque<String> urlQueue, ConcurrentHashMap<String, Integer> libraryCountMap){
            this.urlQueue = urlQueue;
            this.libraryCountMap = libraryCountMap;
        }

        @Override
        public void run() {
            while(!urlQueue.isEmpty()){
                try {
                    Document document = null;
                    document = Crawler.getWebpage(urlQueue.take());
                    List<String> libNames = ParserUtil.convertDocumentToJSLibrary(document);
                    for(String libName: libNames)
                        if(libraryCountMap.containsKey(libName))
                            libraryCountMap.put(libName, libraryCountMap.get(libName) + 1);
                        else
                            libraryCountMap.put(libName, 1);
                } catch (Exception e) {
                    logger.error("Error Occurred: {}", e.getMessage());
                }
            }
        }
    }
}

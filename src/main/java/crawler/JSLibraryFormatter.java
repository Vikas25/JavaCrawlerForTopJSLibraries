package crawler;

import util.Constants;
import util.MapUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JSLibraryFormatter {

    private static BlockingDeque<String> urlBlockedQueue;

    private static ExecutorService threadPoolExecutor;

    private static ConcurrentHashMap<String,Integer> libraryCountMap;

    private GoogleResultFormatter googleResultFormatter;

    public JSLibraryFormatter(GoogleResultFormatter googleResultFormatter){
        this.googleResultFormatter = googleResultFormatter;
    }

    public List<String > getTopFiveJSLibraries(String keyword) throws InterruptedException {

        urlBlockedQueue = new LinkedBlockingDeque<>();
        libraryCountMap = new ConcurrentHashMap<>();

        threadPoolExecutor = Executors.newFixedThreadPool(Constants.THREAD_POOL_SIZE);
        urlBlockedQueue.addAll(googleResultFormatter.getGoogleResults(keyword));

        //Call different webpages from Google search results concurrently using threadPoolExecutor
        IntStream.range(0, Constants.THREAD_POOL_SIZE).forEach(value -> threadPoolExecutor.submit((new Crawler().new AsyncCrawler(urlBlockedQueue, libraryCountMap))));

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(Constants.AWAIT_TERMINATION_TIMEOUT, TimeUnit.SECONDS);

        List<Map.Entry<String, Integer>> topFive = MapUtil.findGreatest(libraryCountMap, Constants.NUMBER_OF_TOP_LIBRARIES);

        return topFive.stream().map(entry -> entry.getKey()).collect(Collectors.toList());
    }

}

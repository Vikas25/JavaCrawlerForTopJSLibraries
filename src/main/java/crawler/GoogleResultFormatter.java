package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import util.Constants;
import util.ParserUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleResultFormatter {

    private static final String GOOGLE_SEARCH_URL = "https://www.google.co.in/search?q=";
    private static final String NO_OF_RESULTS_QUERY = "&num=";
    private static final String GOOGLE_SEARCH_LINKS_QUERY = "a[href]";
    private static final String HREF_QUERY = "href";

    public List<String> getGoogleResults(String keyword) {
        keyword = keyword.replace(" ", "+");

        StringBuilder searchUrl = new StringBuilder()
                .append(GOOGLE_SEARCH_URL)
                .append(keyword)
                .append(NO_OF_RESULTS_QUERY)
                .append(String.valueOf(Constants.NO_OF_RESULTS_FROM_GOOGLE));

        Document document = Crawler.getWebpage(searchUrl.toString());
        Elements elements = document.select(GOOGLE_SEARCH_LINKS_QUERY);
        List<String> results = elements.stream().map(element -> element.attr(HREF_QUERY)).collect(Collectors.toList());

        return ParserUtil.convertUrlsToGoogleResults(results);
    }
}

package util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserUtil {

    private static final String SELECT_SCRIPT_QUERY = "script[src]";
    private static final String GOOGLE_URL_PATTERN = "/url?q=";
    private static final String SPLIT_TO_REMOVE_GOOGLE_TRAIL = "&sa=";
    private static final String JS_EXTENSION = ".js";
    private static final String SRC_ATTRIBUTE_KEY = "src";
    private static final String DELIMETER_FOR_JS_URL= "\\?";

    public static String convertSourceToLibraryName(String jsSource){
        return Arrays.stream(jsSource.split("/")).reduce((first,second)->second).get();
    }

    public static List<String> convertUrlsToGoogleResults(List<String> urlList){
        return urlList.stream().filter(urls -> urls.startsWith(GOOGLE_URL_PATTERN)).map(urls-> new String(urls.substring(GOOGLE_URL_PATTERN.length()).split(SPLIT_TO_REMOVE_GOOGLE_TRAIL)[0])).collect(Collectors.toList());
    }

    public static List<String> convertDocumentToJSLibrary(Document document){
        Elements elements  = document.select(SELECT_SCRIPT_QUERY);
        return elements
                .stream()
                .map(scriptLine -> scriptLine.attr(SRC_ATTRIBUTE_KEY).split(DELIMETER_FOR_JS_URL)[0])
                .filter(jsSrc-> jsSrc.endsWith(JS_EXTENSION))
                .map(jsSrc -> convertSourceToLibraryName(jsSrc))
                .collect(Collectors.toList());
    }

}

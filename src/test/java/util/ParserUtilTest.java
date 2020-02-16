package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserUtilTest {

    private static final String DOCUMENT_HP_PATH = "src/test/resources/DocumentHP";
    private static final String URL_LIST_PATH = "src/test/resources/UrlList";

    @Test
    public void convertSourceToLibraryNameTest(){
        String jsSource = "https://dummyurl.com/tag/js/dummyjslibrary.js";
        String expected = "dummyjslibrary.js";
        Assert.assertEquals(expected,ParserUtil.convertSourceToLibraryName(jsSource));
    }

    @Test
    public void convertUrlsToGoogleResultsTest() throws IOException {
        List<String> urlList = getMockUrls();
        List<String> expected = getMockResultsfromUrls();
        Assert.assertTrue(expected.containsAll(ParserUtil.convertUrlsToGoogleResults(urlList)));
    }

    @Test
    public void convertDocumentToJSLibraryTest() throws IOException {
        Document document = Jsoup.parse(Files.lines(Paths.get(DOCUMENT_HP_PATH)).collect(Collectors.joining(System.lineSeparator())));
        List<String> expected = getMockLibraryNamesFromDocument();
        Assert.assertTrue(expected.containsAll(ParserUtil.convertDocumentToJSLibrary(document)));
    }

    private List<String> getMockUrls() throws IOException {
        return Arrays.asList(
                Files.lines(Paths.get(URL_LIST_PATH))
                        .collect(Collectors.joining(System.lineSeparator()))
                        .split(";")[0].split(","));
    }

    private List<String> getMockResultsfromUrls() throws IOException {
        return Arrays.asList(
                Files.lines(Paths.get(URL_LIST_PATH))
                        .collect(Collectors.joining(System.lineSeparator()))
                        .split(";")[1].split(","));
    }

    private List<String> getMockLibraryNamesFromDocument(){
        return  Arrays.asList(new String[]{"matchMedia.js","jquery-1.11.1.min.js","ias.js","fw-loader.js","common.js","dictionary.js","thesaurus.js"});
    }

}

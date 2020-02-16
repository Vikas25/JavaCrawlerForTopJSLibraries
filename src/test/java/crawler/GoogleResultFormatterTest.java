package crawler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleResultFormatterTest {
    private static final String DOCUMENT_SC_PATH = "src/test/resources/DocumentHP";
    private static final String HP_GOOGLE_RESULT_LIST_PATH = "src/test/resources/HP-google-result-list";
    private GoogleResultFormatter googleResultFormatter;
    private static final Double THRESHOLD = 80.0;

    @Before
    public void setup(){
        googleResultFormatter = new GoogleResultFormatter();
    }

    @Test
    public void getGoogleResultsTest() throws IOException {
        String keyword = "Harry Potter";
        List<String> googleResults = getMockResultsForHarryPotter();
        int count = 0;
        List<String> runtimeGoogleResults = googleResultFormatter.getGoogleResults(keyword);
        for(String googleResult: runtimeGoogleResults){
            if(googleResults.contains(googleResult)){
                count++;
            }
        }
        Assert.assertTrue((count*100/runtimeGoogleResults.size()) > THRESHOLD);
    }

    private List<String> getMockResultsForHarryPotter() throws IOException {
        List<String> googleResults = Arrays.asList(
                Files.lines(Paths.get(HP_GOOGLE_RESULT_LIST_PATH))
                        .collect(Collectors.joining(System.lineSeparator()))
                        .split(","));
        return googleResults;
    }

}

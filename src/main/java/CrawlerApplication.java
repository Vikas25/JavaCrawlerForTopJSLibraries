import crawler.GoogleResultFormatter;
import crawler.JSLibraryFormatter;

import java.util.List;
import java.util.Scanner;

public class CrawlerApplication {
    public static void main(String[] args) throws InterruptedException {

        //Using Dependency Inversion to create JSLibraryFormatter object
        GoogleResultFormatter googleResultFormatter = new GoogleResultFormatter();
        JSLibraryFormatter jsLibraryFormatter = new JSLibraryFormatter(googleResultFormatter);

        //STDIN
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input keyword for Google Search");
        String keyword = scanner.next();

        //STDOUT
        List<String> topFiveLibraries = jsLibraryFormatter.getTopFiveJSLibraries(keyword);
        System.out.println("Top 5 Javascript Libraries Used are:");
        System.out.println(topFiveLibraries.toString());
    }
}

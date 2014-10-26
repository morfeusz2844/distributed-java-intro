package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import java.util.concurrent.Callable;

public class WordCounter {

    private final String documentUrl;
    private final String wordToCount;
    private final Callable<Integer> callable = new Callable<Integer>() {

        public Integer call() throws Exception {
            HtmlDocument htmlDocument = new GazetaHtmlDocument(documentUrl);
            String content = htmlDocument.getContent().toLowerCase();
            return common.StringUtils.countOccurrences(content, wordToCount);
            
        }
    };

    public WordCounter(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }
}

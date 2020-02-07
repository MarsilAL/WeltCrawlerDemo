package weltcrawlerdemo.domain;

import java.util.List;
import weltcrawlerdemo.infrastructure.IRssReader;

/**
 * ArticleUseCase is responsible for getting articles of a given category
 */
public class ArticleUseCase {

    private final IRssReader reader;

    public ArticleUseCase(IRssReader reader) {
        this.reader = reader;
    }

    // overloading :-)
    public List<Article> getArticlesForCategory(String cat) {
        return getArticlesForCategory(cat, 10);
    }

    // gets the articles of a given category by asking the rss reader
    public List<Article> getArticlesForCategory(String cat, int maxSize) {
        String url = getUrlForCategory(cat);
        return this.reader.fetchArticles(url, maxSize);
    }
    
    // returns the url for a given category
    private String getUrlForCategory(String category) {
        if (category.equals("politik"))
            return "https://www.welt.de/feeds/section/politik.rss";
        if (category.equals("sport"))
            return "https://www.welt.de/feeds/section/sport.rss";      
        return "";
    }
}

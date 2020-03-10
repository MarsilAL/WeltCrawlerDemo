package weltcrawlerdemo.domain;

import java.util.List;
import weltcrawlerdemo.infrastructure.rss.IRssReader;

/**
 * ArticleUseCase is responsible for getting articles of a given category
 */
public class ArticleUseCase {

    private final IRssReader reader;

    public ArticleUseCase(IRssReader reader) {
        this.reader = reader;                               // MMC=0
    }

    // overloading :-)
    public List<Article> getArticlesForCategory(String cat) {
        return getArticlesForCategory(cat, 20);
    }

    // gets the articles of a given category by asking the rss reader
    public List<Article> getArticlesForCategory(String cat, int maxSize) {
        String url = getUrlForCategory(cat);                                // MMC=0
        return this.reader.fetchArticles(url, maxSize);
    }
    
    // returns the url for a given category
    public String getUrlForCategory(String category) {

        if (category.equals("politik"))
            return "https://www.welt.de/feeds/section/politik.rss";

        if (category.equals("sport"))
            return "https://www.welt.de/feeds/section/sport.rss";        // MMC=3

        if (category.equals("wirtschaft"))
            return "https://www.welt.de/feeds/section/wirtschaft.rss"; 
        
        if (category.equals("kultur"))
            return "https://www.welt.de/feeds/section/kultur.rss"; 

        if (category.equals("wissenschaft"))
            return "https://www.welt.de/feeds/section/wissenschaft.rss"; 
            
        return "";
    }

    public static String getCategoryForUrl(String url) {

        if(url.equals("https://www.welt.de/feeds/section/politik.rss")){
            return "politik";
        }
        if(url.equals("https://www.welt.de/feeds/section/sport.rss")){
            return "sport";
        }
        if(url.equals("https://www.welt.de/feeds/section/wirtschaft.rss")){
            return "wirtschaft";
        }
        if(url.equals("https://www.welt.de/feeds/section/kultur.rss")){
            return "kultur";
        }
        if(url.equals("https://www.welt.de/feeds/section/wissenschaft.rss")){
            return "wissenschaft";
        }


        return "";
        
    }
}

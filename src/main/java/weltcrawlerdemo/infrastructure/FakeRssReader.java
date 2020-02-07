package weltcrawlerdemo.infrastructure;
// the fake rss reader always returns the same 5 articels

import java.util.ArrayList;
import java.util.List;

import weltcrawlerdemo.domain.Article;

public class FakeRssReader implements IRssReader {

    @Override
    public List<Article> fetchArticles(String urlAddress, int maxSize) {
        List<Article> articles = new ArrayList<Article>();

        for(int i=0; i<maxSize; i++){
            Article article = new Article("sdasf", "dsd");
            articles.add(article);
        }

        return articles;
    }

}

package weltcrawlerdemo.infrastructure;

import java.util.ArrayList;
import java.util.List;

import weltcrawlerdemo.domain.Article;

/* this class is the abstraction of the db */
public class ArticleRepository {

    public void save(Article article) {

    }

    public List<Article> search(String term, String category) {

        return new ArrayList<>();

    }
}
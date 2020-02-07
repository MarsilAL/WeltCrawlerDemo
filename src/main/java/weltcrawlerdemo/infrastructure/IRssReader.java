package weltcrawlerdemo.infrastructure;

import weltcrawlerdemo.domain.Article;
import java.util.*;

public interface IRssReader{
    public List<Article> fetchArticles(final String urlAddress, int maxSize);
}
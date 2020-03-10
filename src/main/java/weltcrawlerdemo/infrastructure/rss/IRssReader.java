package weltcrawlerdemo.infrastructure.rss;

import weltcrawlerdemo.domain.Article;
import java.util.*;

public interface IRssReader{
    public List<Article> fetchArticles(final String urlAddress, int maxSize);
}
// MMC=0
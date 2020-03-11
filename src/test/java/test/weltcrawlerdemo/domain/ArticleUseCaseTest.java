package test.weltcrawlerdemo.domain;

import org.junit.*;

import java.util.List;

import weltcrawlerdemo.domain.*;
import weltcrawlerdemo.infrastructure.rss.FakeRssReader;
import weltcrawlerdemo.infrastructure.rss.IRssReader;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.not;

public class ArticleUseCaseTest {
    //Test 1
/**
    @Test
    public void getArticlesForCategory_should_return_10_articles_given_no_max() {

        int expectedSize = 10;
        IRssReader reader = new FakeRssReader();
        ArticleUseCase articleUsecase = new ArticleUseCase(reader);

        List<Article> articles = articleUsecase.getArticlesForCategory("category");

        int actualSize = articles.size();
        assertEquals(expectedSize, actualSize);
    }
*/
    @Test
    public void getArticlesForCategory_should_return_max_articles_given_max() {

        int expectedSize = 23;
        IRssReader reader = new FakeRssReader();
        ArticleUseCase articleUsecase1 = new ArticleUseCase(reader);

        List<Article> articles = articleUsecase1.getArticlesForCategory("category", expectedSize);
        
        int actualSize = articles.size();
        assertEquals(expectedSize, actualSize);
    }
}






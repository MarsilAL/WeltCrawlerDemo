package weltcrawlerdemo.domain;

import java.util.ArrayList;
import java.util.List;
import weltcrawlerdemo.infrastructure.ArticleRepository;

public class StorageUseCase {

    private ArticleUseCase articleUseCase;

    private ArticleRepository repository;

    public StorageUseCase(ArticleUseCase articleUseCase, ArticleRepository repository) {
        this.articleUseCase = articleUseCase;
        this.repository = repository;
    }

    public void fetchAndStoreArticles() {

        List<Article> politikArticles = this.articleUseCase.getArticlesForCategory("politik");
        for (Article article : politikArticles) {


        this.repository.save(article);
        // System.out.println(article.getTitle());
        }

        List<Article> sportArticles = this.articleUseCase.getArticlesForCategory("sport");
        for (Article article : sportArticles) {

             this.repository.save(article);
        }
        List<Article> WirtschaftArticles = this.articleUseCase.getArticlesForCategory("wirtschaft");
        for (Article article : WirtschaftArticles) {

             this.repository.save(article);
        }
        List<Article> kulturArticles = this.articleUseCase.getArticlesForCategory("kultur");
        for (Article article : kulturArticles) {

             this.repository.save(article);
        }
        List<Article> wissenschaftArticles = this.articleUseCase.getArticlesForCategory("wissenschaft");
        for (Article article : wissenschaftArticles) {

             this.repository.save(article);
        }
    }




    public List<Article> searchArticles(String term, String category) {

        return new ArrayList<Article>();
    }
}
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

    public void fetchAndStoreArticle(){

            List<Article> politikArticles = this.articleUseCase.getArticlesForCategory("politik");

            for(Article article : politikArticles){
                this.repository.save(article);
            }

    } 
     
    public List<Article> searchArticles(String term, String category){

        return new ArrayList<Article>();
    }
}
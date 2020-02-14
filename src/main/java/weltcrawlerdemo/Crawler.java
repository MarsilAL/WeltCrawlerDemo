package weltcrawlerdemo;

import weltcrawlerdemo.infrastructure.*;
import weltcrawlerdemo.domain.*;

/**
 * IDFK
 */
public class Crawler {
    public static void main(final String[] arguments) {

        // rss reader fetches the feeds
        RssReader reader = new RssReader();

        // usecase is being used by cli to orchestrate the action
        ArticleUseCase articleUseCase = new ArticleUseCase(reader);
       
        ArticleRepository repository = new ArticleRepository();

        StorageUseCase storageUseCase = new StorageUseCase(articleUseCase, repository);



        //OLD understands the users input and delegates to the usecase

        storageUseCase.fetchAndStoreArticle();

    }
}
// MMC=1
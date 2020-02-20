package weltcrawlerdemo.infrastructure;

import java.util.Date;

import java.sql.Connection;
import java.util.TimerTask;
import weltcrawlerdemo.domain.*;

public class ExecuteTimer extends TimerTask {

    @Override
    public void run() {

        Connection connection = PostgresClient.connect("localhost", "postgres", "ideas", "weltstore");

        if (connection == null) {
            System.err.println("failed to connect to Database");
            System.exit(1);
        } else {
            System.out.println("\nconnected");
        }

        System.out.println("started at:"+new Date());




        ArticleRepository repository = new ArticleRepository(connection);

        RssReader reader = new RssReader();

        ArticleUseCase articleUseCase = new ArticleUseCase(reader);
        StorageUseCase storageUseCase = new StorageUseCase(articleUseCase, repository);

        storageUseCase.fetchAndStoreArticles();



        System.out.println("finished at:"+new Date());
    }

}
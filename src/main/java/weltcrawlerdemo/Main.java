package weltcrawlerdemo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import weltcrawlerdemo.domain.ArticleUseCase;
import weltcrawlerdemo.domain.StorageUseCase;
import weltcrawlerdemo.infrastructure.db.ArticleRepository;
import weltcrawlerdemo.infrastructure.db.PostgresClient;
import weltcrawlerdemo.infrastructure.rss.RssReader;
import weltcrawlerdemo.infrastructure.web.HealthEndpoint;
import weltcrawlerdemo.infrastructure.web.LatestArticlesHandler;
import weltcrawlerdemo.infrastructure.web.PingPongHandler;

import java.sql.Connection;
import java.util.Date;
import java.util.Timer;


public class Main {
    public static void main(final String[] arguments) throws Exception {

        // read environment variables
        String dbHost = System.getenv("DB_HOST");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");
       // String dbPort = System.getenv("DB_PORT");

        if (dbHost == null || dbUser == null || dbPass == null || dbName == null || dbHost.isBlank() || dbUser.isBlank()
                || dbPass.isBlank() || dbName.isBlank()) {
            System.err.println("missing environment variables");
            System.exit(1);
        } else {
            System.err.printf("Using host:%s, user:%s, pass:%s, dbName:%s\n ", dbHost, dbUser, "The Pass", dbName);
        }

        // connect to db
        Connection connection = PostgresClient.connect(dbHost, dbUser, dbPass, dbName);
        ;
        if (connection == null) {
            System.err.println("failed to connect to Database");
            System.exit(1);
        } else {
            System.out.println("connected:" + new Date());
        }

        // create all the dependencies
        RssReader reader = new RssReader();
        ArticleRepository repository = new ArticleRepository(connection);
        ArticleUseCase articleUseCase = new ArticleUseCase(reader);
        StorageUseCase storageUseCase = new StorageUseCase(articleUseCase, repository);

        // create the task to run
        FetchAndStoreTask timerTask = new FetchAndStoreTask(storageUseCase);

        // schedule the task
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000, 5 * 60 * 1000);
        System.out.println("FetchAndStoreTask started");

        // configure web stuff
        final ContextHandler pingpong = new ContextHandler("/api/pingpong");
        final ContextHandler search = new ContextHandler("/api/search");
        final ContextHandler health = new ContextHandler("/health");

        health.setHandler(new HealthEndpoint());
        pingpong.setHandler(new PingPongHandler());
        search.setHandler(new LatestArticlesHandler(storageUseCase));

        ContextHandlerCollection contexts = new ContextHandlerCollection(health, pingpong, search);

        final String port = System.getenv().getOrDefault("PORT", "8888");
        final Server server = new Server(Integer.parseInt(port));

        server.setHandler(contexts);
        server.start();
        server.join();
    }

}
package weltcrawlerdemo;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import weltcrawlerdemo.domain.ArticleUseCase;
import weltcrawlerdemo.domain.StorageUseCase;
import weltcrawlerdemo.infrastructure.*;

import weltcrawlerdemo.infrastructure.PostgresClient;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Timer;

public class Crawler {
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

        final ContextHandler health = new ContextHandler("/health");
        final ContextHandler api = new ContextHandler("/api");
        final ContextHandler pingpong = new ContextHandler("/api/pingpong");
        health.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.getWriter().println("UP");
                baseRequest.setHandled(true); // important!
            }
        });

        api.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.getWriter().println("this is api");
                baseRequest.setHandled(true);
            }
        });
        pingpong.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

                String echo = "";

                if(request.getParameter("echo") != null){
                    echo = request.getParameter("echo");
                }
                if(!echo.isEmpty()){

                    if(echo.equals("ping")){

                        response.getWriter().println("pong");

                    } else if(echo.equals("pong")){

                        response.getWriter().println("ping");

                    }  else {


                        response.setStatus(400);
                    }
                } else {

                    response.getWriter().println("please enter a parameter ");

                }
                baseRequest.setHandled(true);

            }

        });

        ContextHandlerCollection contexts = new ContextHandlerCollection(health, api, pingpong);
        final String port = System.getenv().getOrDefault("PORT", "8888");
        final Server server = new Server(Integer.parseInt(port));
        server.setHandler(contexts);
        server.start();
        server.join();
    }

}
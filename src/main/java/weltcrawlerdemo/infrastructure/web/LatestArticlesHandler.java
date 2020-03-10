package weltcrawlerdemo.infrastructure.web;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import weltcrawlerdemo.domain.Article;
import weltcrawlerdemo.domain.StorageUseCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LatestArticlesHandler extends AbstractHandler {

    private final StorageUseCase storageUsageCase;

    public LatestArticlesHandler(StorageUseCase useCase) {
        this.storageUsageCase = useCase;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {

        List<Article> articleList = storageUsageCase.getLatestArticles();

        // todo convert articleList to json

        // todo return the json

        baseRequest.setHandled(true);
    }
}

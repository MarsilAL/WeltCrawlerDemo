package weltcrawlerdemo.infrastructure.web;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONArray;
import weltcrawlerdemo.domain.Article;
import weltcrawlerdemo.domain.StorageUseCase;
import weltcrawlerdemo.infrastructure.db.ArticleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

public class LatestArticlesHandler extends AbstractHandler {

    private final StorageUseCase storageUsageCase;

    public LatestArticlesHandler(StorageUseCase useCase) {
        this.storageUsageCase = useCase;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String category ="";

        if(request.getParameter("category") != null){
            category = request.getParameter("category");
        }

        List<Article> articleList = storageUsageCase.getLatestArticles(category);
        JSONArray respsone = new JSONArray(articleList);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(respsone);

        baseRequest.setHandled(true);
    }
}

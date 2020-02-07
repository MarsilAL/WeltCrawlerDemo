package weltcrawlerdemo.infrastructure;

import java.util.List;

import weltcrawlerdemo.domain.Article;
import weltcrawlerdemo.domain.ArticleUseCase;

public class Cli {

    final private ArticleUseCase usecase;

    /** constructor */
    public Cli(ArticleUseCase useCase) {
        this.usecase = useCase;
    }

    /**
     * handles users input and delegates to the usecase
     */
    public String handleInput(String[] args) {

        // not enough args
        if (args.length < 1) {
            return getHelp();
        }

        // too many args
        if (args.length > 2) {
            return getHelp();
        }

        // extract category
        String category = args[0];
        int maxSize = aritclesMaxSize(args);
        int maxCount = 10;

        // set max count if given
        if (args.length == 2) {
            maxCount = Integer.parseInt(args[1]);
        }

        // do the action
        switch (category) {
        case "politik":
            return articlesAsString(usecase.getArticlesForCategory(category, maxSize));
        case "sport":
            return articlesAsString(usecase.getArticlesForCategory(category, maxSize));
        }

        return getHelp();
    }

    /**
     * ^ returns the list of articles as a nice string table
     * 
     * @param articles
     * @return
     */
    private String articlesAsString(List<Article> articles) {
        String response = ""; // response variable is initialized to empty string

        if (articles == null) {
            return response;
        }

        int counter = 1; // counter

        for (Article article : articles) { // for every article in given articles
            response += counter + ": " // save to response a new string
                    + "title: " + article.getTitle() + "\n"; // with the title of the article
            counter++; // increase the counter for the next article

        }
        return response; // returns the final string
    }

    private String getHelp() {
        return "\n **** Help ****" + "please enter the category\nsport or politik";
    }

    private int aritclesMaxSize(String args[]) {
        int aritclesMaxSize = 1;
        if (args.length == 2) {
            aritclesMaxSize = Integer.parseInt(args[1]);
        }
        return aritclesMaxSize;
    }

}

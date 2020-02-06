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
        ArticleUseCase usecase = new ArticleUseCase(reader);

        // understands the users input and delegates to the usecase
        Cli cli = new Cli(usecase);
        System.out.println(cli.handleInput(arguments));
    }
}
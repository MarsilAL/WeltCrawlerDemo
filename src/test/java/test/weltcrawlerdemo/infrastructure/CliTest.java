package test.weltcrawlerdemo.infrastructure;

import org.junit.*;

import weltcrawlerdemo.infrastructure.*;
import weltcrawlerdemo.domain.*;
import weltcrawlerdemo.infrastructure.rss.FakeRssReader;
import weltcrawlerdemo.infrastructure.rss.IRssReader;

import static org.junit.Assert.*; 
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class CliTest {
    @Test
    public void Cli_should_return_help_when_no_args_given(){
        // setup
        final IRssReader reader = new FakeRssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        final String[] args = {};

        // when
        final String actual = cli.handleInput(args);
        
        // then
        assertThat(actual, containsString("**** Help ****"));
    }

    @Test
    public void Cli_should_not_return_help_when_category_given(){
        // setup
        final IRssReader reader = new FakeRssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        final String[] args = {"politik"};

        final String actual = cli.handleInput(args);
        
        // then
        assertThat(actual, not(containsString("**** Help ****")));     
    } 

    @Test
    public void Cli_should_return_help_when_too_many_arguments_given(){
        // setup
        final IRssReader reader = new FakeRssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        String[] args = {"Sport", "3", "faefe"};

        final String actual = cli.handleInput(args);
        // then
        assertThat(actual, containsString("**** Help ****"));   
    } 

    @Test
    public void Cli_should_return_help_when_invalid_arguments_given(){
        // setup
        final IRssReader reader = new FakeRssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        String[] args =  {"faefe"};

        final String actual = cli.handleInput(args);
        // then
        assertThat(actual, containsString("**** Help ****"));   
    } 

    @Test
    public void Cli_should_not_return_help_when_category_and_maxSize_given(){

        // setup
        final IRssReader reader = new FakeRssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        final String[] args = {"sport", "8"};

        final String actual = cli.handleInput(args);
        
        // then
        assertThat(actual, not(containsString("**** Help ****"))); 
    }
}

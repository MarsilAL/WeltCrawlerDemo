package test.weltcrawlerdemo.infrastructure;

import org.junit.*;
import org.hamcrest.core.StringContains;


import weltcrawlerdemo.infrastructure.*;
import weltcrawlerdemo.domain.*;


import static org.junit.Assert.*; 
import static org.hamcrest.CoreMatchers.containsString;

public class CliTest {
    //Test 1

    @Test
    public void Cli_should_return_help_when_no_args_given(){
        // setup
        final RssReader reader = new RssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        final String[] input = {};

        // when
        final String actual = cli.handleInput(input);
        
        // then
        assertThat(actual, containsString("**** Help ****"));
    }

    //Test 2

    @Test
    public void Cli_should_return_results_when_cateogry_given(){
        // todo
        // setup
        final RssReader reader = new RssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        final String[] args = {"politik"};

        final String actual = cli.handleInput(args);
        // then
        assertThat(actual, containsString("Politik"));     
    } 

    //Test 3

    @Test
    public void Cli_should_return_help_when_too_many_arguments_given(){
        // todo
                // setup
        final RssReader reader = new RssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        String[] args = {"Sport", "3", "faefe"};

        final String actual = cli.handleInput(args);
        // then
        assertThat(actual, containsString("**** Help ****"));   
    } 

    //Test 4

    @Test
    public void Cli_should_return_help_when_invalid_arguments_given(){
        // todo
                // setup
        final RssReader reader = new RssReader();
        final ArticleUseCase usecase = new ArticleUseCase(reader);

        // given
        final Cli cli = new Cli(usecase);
        String[] args =  {"faefe"};

        final String actual = cli.handleInput(args);
        // then
        assertThat(actual, containsString("**** Help ****"));   
    } 





}

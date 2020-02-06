package weltcrawlerdemo.domain;

public class Article {
    final private String title;
    final private String link;

    public Article(String title, String link ){
        this.title = title;
        this.link = link;
    }
    public String getTitle(){
        return title;
    }
    public String getLink(){
        return link;
    }
}
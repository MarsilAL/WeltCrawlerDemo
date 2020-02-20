package weltcrawlerdemo.domain;

public class Article {
    final private String title;
    final private String subcategory;
    final private String category;
    final private int guid;
    final private String pubdate;
    final private String description;
    final private String link;

    public Article(int guid, String subcategory, String category, String title, String pubdate, String description,
            String link) {
        this.guid = guid;
        this.category = category;
        this.subcategory = subcategory;
        this.title = title;
        this.pubdate = pubdate;
        this.description = description;
        this.link = link;

    }

    public String getTitle() {
        return title;
    }

    public int getGuid() {
        return guid;
    }

    public String getPubDate() {
        return pubdate;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subcategory;
    }

    public String getLink() {
        return link;
    }

}
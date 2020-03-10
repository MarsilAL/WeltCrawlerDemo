package weltcrawlerdemo.infrastructure.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import weltcrawlerdemo.domain.Article;

/* this class is the abstraction of the db */
public class ArticleRepository {
    private final String tableName = "article_store";
    private final Connection connection;

    public ArticleRepository(Connection c) {
        this.connection = c;
    }

    public void save(Article article) {

        // System.out.println("saving article ... " + article);

        Statement stmt = null;
        Statement st = null;

        try {

            st = connection.createStatement();

            String query = "SELECT guid FROM article_store WHERE guid ='" + article.getGuid() + "';";

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                System.out.println("you already have the article with the guid --> " + article.getGuid()
                        + " <--  in your datebase.");
                return;

            }

            st.close();

            stmt = connection.createStatement();

            String sql = "INSERT INTO " + tableName + "(category, subcategory, title, guid, description, pubdate, link) " + "VALUES ('"
                    + article.getCategory() + "', '" + article.getSubCategory() + "' , '" + article.getTitle() + "', '" + article.getGuid() + "', '"
                    + article.getDescription() + "', '" + article.getPubDate() + "', '" + article.getLink() + "');";

            // System.err.println(sql);

            stmt.executeUpdate(sql);

            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public List<Article> search(String searchterm) throws SQLException {
        List<Article> result = new ArrayList<>();
        // TODO
        return result;
    }

    public List<Article> latest() {
        List<Article> result = new ArrayList<>();

        try {
            Statement sts = null;
            try {
                sts = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String query = "SELECT guid, link, subcategory, category, title, pubdate, description FROM article_store ORDER BY pubdate LIMIT 10";
            ResultSet rss = sts.executeQuery(query);

            // go through result and create articles from them
            if (rss.next()) {

                // get the data from the result
                int guid = rss.getInt("guid");
                String link = rss.getString("link");
                String subcategory = rss.getString("subcategory");
                String category = rss.getString("category");
                String title = rss.getString("title");
                Timestamp pubdate = rss.getTimestamp("pubdate");
                String description = rss.getString("description");

                // use the data to create a new article
                Article article = new Article(guid, subcategory, category, title, pubdate.toString(), description, link);

                // add to result
                result.add(article);
            }
            sts.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return result;
    }

}
package weltcrawlerdemo.infrastructure;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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

    public List<Article> search(String term, String category) {

        return new ArrayList<>();

    }
}
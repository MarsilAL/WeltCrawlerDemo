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
/**
    public List<Article> searchArticles(String searchterm, String searchcategory) throws SQLException {
        try {
            List<Article> searchresult = new ArrayList<>();
            Statement sts = null;
            String sqlQuery = "";
            if (searchterm.isEmpty() && searchcategory.isEmpty()) {
                sqlQuery = "SELECT guid, link, subcategory, category, title, pubdate, description FROM article_store ORDER BY pubdate LIMIT 10";

                sts = connection.prepareStatement(sqlQuery);
                System.out.println("results for : " + searchcategory + ", " + searchterm);
            } else (!searchterm.isEmpty() && searchcategory.isEmpty()) {
                sqlQuery = "";
            }

            ResultSet resultSet = sts.executeQuery(sqlQuery);
            for (int i = 0; i < 10; i++) {

                if (resultSet.next()) {

                    // get the data from the result
                    int guid = resultSet.getInt("guid");
                    String link = resultSet.getString("link");
                    String subcategory = resultSet.getString("subcategory");
                    String category = resultSet.getString("category");
                    String title = resultSet.getString("title");
                    Timestamp pubdate = resultSet.getTimestamp("pubdate");
                    String description = resultSet.getString("description");

                    // use the data to create a new article
                    Article article = new Article(guid, subcategory, category, title, pubdate.toString(), description, link);

                    // add to result
                    searchresult.add(article);
                }
            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        return searchresult;
    }
*/
    public List<Article> articleSearch(String searchcategory) {
        List<Article> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = null;
            String sqlQuery = "";
            if (searchcategory.isEmpty()) {
                sqlQuery = "SELECT guid, link, subcategory, category, title, pubdate, description FROM article_store ORDER BY pubdate LIMIT 10";
                preparedStatement = connection.prepareStatement(sqlQuery);
                System.out.println("Here is The latest 10 TopNews, Enter a term and a category to get specific news");
            } else if(!searchcategory.isEmpty()){
                sqlQuery = "SELECT guid, link, subcategory, category, title, pubdate, description FROM article_store WHERE category = ? ORDER BY pubdate LIMIT 10";
                preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1,  searchcategory );
                System.out.println("results for :  " + searchcategory );
                System.out.println(preparedStatement);
            }
            System.out.println("");
            // go through result and create articles from them
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < 10; i++) {

                if (resultSet.next()) {

                    // get the data from the result
                    int guid = resultSet.getInt("guid");
                    String link = resultSet.getString("link");
                    String subcategory = resultSet.getString("subcategory");
                    String category = resultSet.getString("category");
                    String title = resultSet.getString("title");
                    Timestamp pubdate = resultSet.getTimestamp("pubdate");
                    String description = resultSet.getString("description");

                    // use the data to create a new article
                    Article article = new Article(guid, subcategory, category, title, pubdate.toString(), description, link);

                    // add to result
                    result.add(article);
                }
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return result;
    }

}
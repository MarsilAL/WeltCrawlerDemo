package weltcrawlerdemo.infrastructure.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CallDatabase {
    private final Connection connection;

    public CallDatabase(Connection connection) {
        this.connection = connection;
    }
    public String dataFetch(){
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM article_store ORDER BY pubdate LIMIT 10 ";
            ResultSet result = stmt.executeQuery(sql);
            if (result.next()) {
                String resultString = result.getString(1);
                System.out.println("results: '" + resultString + "'");
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "";
    }
}

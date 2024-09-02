package zuper.programmer.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        String sql = """
                SELECT id, comment, email FROM customers
                """;
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String id = resultSet.getString("id");
            String comment = resultSet.getString("customers.comment");
            String email = resultSet.getString("email");

            System.out.println(
                    String.join(", ", id, comment, email)
            );
        }

        resultSet.close();
        statement.close();

        connection.close();
    }
}

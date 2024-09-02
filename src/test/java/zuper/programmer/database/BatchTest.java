package zuper.programmer.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
    @Test
    void testBatchStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES('fulan@test', 'hi')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();

    }

    @Test
    void testBatchPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "fulan@test");
            preparedStatement.setString(2, "hi");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();

    }
}

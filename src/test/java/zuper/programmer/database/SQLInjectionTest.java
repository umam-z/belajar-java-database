package zuper.programmer.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {
    @Test
    void testSQLInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "admin";

        String sql = "SELECT username, password FROM admin WHERE username = '" + username +
                "' AND password = '" +password+ "'";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // sukses login
            System.out.println("Sukses Login: " + resultSet.getString("username"));
        } else {
            // gagal login
            System.out.println("Gagal Login");
        }

        resultSet.close();
        statement.close();

        connection.close();
    }
}

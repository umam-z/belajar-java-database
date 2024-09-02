package zuper.programmer.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testDatabaseMetaData()throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet belajarJavaDatabase = metaData.getTables("belajar_java_database", null, null, null);
        while (belajarJavaDatabase.next()) {
            String tableName = belajarJavaDatabase.getString("TABLE_NAME");
            System.out.println("Table: " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
        System.out.println(parameterMetaData.getParameterTypeName(1));

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM customers";
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metaData = resultSet.getMetaData();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.println("Name: " + metaData.getColumnName(i));
            System.out.println("Type: " + metaData.getColumnType(i));
            System.out.println("Type Name: " + metaData.getColumnTypeName(i));

            if (metaData.getColumnType(i) == Types.INTEGER) {
                System.out.println("Ini adalah integer");
            }
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

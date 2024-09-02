package zuper.programmer.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {
    @Test
    void testDate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_time(sample_time, sample_date, sample_timestamp)
                VALUES (?, ?, ?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setTime(1, new Time(System.currentTimeMillis()));
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testDateQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "SELECT * FROM sample_time";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Time sampleTime = resultSet.getTime("sample_time");
            System.out.println("Time: " + sampleTime);

            Date sampleDate = resultSet.getDate("sample_date");
            System.out.println("Date: " + sampleDate);

            Timestamp sampleTimestamp = resultSet.getTimestamp("sample_timestamp");
            System.out.println("Timestamp: " + sampleTimestamp);

        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

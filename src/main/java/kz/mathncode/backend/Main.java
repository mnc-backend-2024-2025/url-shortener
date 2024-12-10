package kz.mathncode.backend;

import com.github.javafaker.Faker;
import kz.mathncode.backend.entity.factory.faker.UserFakerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        //JDBC -- Java Database Connectivity
        String url = "jdbc:postgresql://localhost:5432/url-shortener";

        Connection connection = DriverManager.getConnection(url, "admin", "admin");
        String query = """
                CREATE TABLE users(
                    ID INTEGER PRIMARY KEY,
                    FIRST_NAME VARCHAR(100) STRING
                );
        """;

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
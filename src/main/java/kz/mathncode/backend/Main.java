package kz.mathncode.backend;

import com.github.javafaker.Faker;
import kz.mathncode.backend.dao.UserDAO;
import kz.mathncode.backend.entity.factory.faker.UserFakerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //JDBC -- Java Database Connectivity
        // url-shortener - название базы данных

        //Создать 150 пользователей
        String url = "jdbc:postgresql://localhost:5432/url-shortener?sslmode=disable";
        Connection connection = DriverManager.getConnection(url, "postgres", "admin");

        UserDAO dao = new UserDAO(connection);
        UserFakerFactory factory = new UserFakerFactory(new Faker());

        dao.createTableIfNotExists();
        dao.create(factory.produce());
    }
}
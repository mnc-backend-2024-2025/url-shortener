package kz.mathncode.backend;

import com.github.javafaker.Faker;
import kz.mathncode.backend.dao.UserDAO;
import kz.mathncode.backend.entity.User;
import kz.mathncode.backend.entity.factory.faker.UserFakerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        //JDBC -- Java Database Connectivity
        String url = "jdbc:postgresql://localhost:5432/url-shortener?sslmode=disable";
        Connection connection = DriverManager.getConnection(url, "postgres", "admin");

        UserDAO dao = new UserDAO(connection);
        UserFakerFactory factory = new UserFakerFactory(new Faker());

        dao.createTableIfNotExists();
        dao.create(factory.produce());

        var readUser = dao.readById(UUID.fromString("9b902fed-19dc-4f78-8729-b7584577fecd"));
        readUser.setEmail("changed@gmail.com");
        dao.update(readUser, readUser.getId());

        dao.deleteById(UUID.fromString("e892d011-e418-41d6-a54c-4f958e344ea4"));
    }
}
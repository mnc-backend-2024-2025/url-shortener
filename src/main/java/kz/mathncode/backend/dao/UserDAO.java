package kz.mathncode.backend.dao;

import kz.mathncode.backend.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class UserDAO extends AbstractDAO<User, UUID>{
    public UserDAO(Connection connection) {
        super(connection, User.class, UUID.class);
    }

    @Override
    public void create(User entity) throws SQLException {
        var queryFormat = """
                INSERT INTO "user" ("id", "firstName", "lastName", "email", "createdAt")
                VALUES ('%s', '%s', '%s', '%s', %d);
                """;
        var query = String.format(queryFormat,
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt().toInstant().getEpochSecond()
                );

        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public User readById(UUID uuid) throws SQLException {
        var queryFormat = """
                SELECT "id", "firstName", "lastName", "email", "createdAt"
                FROM "user"
                WHERE id = '%s';
                """;
        var query = String.format(queryFormat, uuid);
        var statement = getConnection().createStatement();
        var resultSet = statement.executeQuery(query);

        resultSet.next();
        var id = resultSet.getString("id");
        var firstName = resultSet.getString("firstName");
        var lastName = resultSet.getString("lastName");
        var email = resultSet.getString("email");
        var createdAtTS = resultSet.getInt("createdAt");
        var createdAt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(createdAtTS), ZoneId.of("Asia/Almaty"));

        return new User(UUID.fromString(id), firstName, lastName, email, createdAt);

    }

    @Override
    public void update(User entity, UUID uuid) throws SQLException {
        var queryFormat = """
                UPDATE "user"
                SET "firstName" = '%s', "lastName" = '%s', "email" = '%s', "createdAt" = '%s'
                WHERE "id" = '%s';
                """;
        var query = String.format(queryFormat,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt().toInstant().getEpochSecond(),
                entity.getId()
        );

        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void deleteById(UUID uuid) throws SQLException {
        var queryFormat = """
                DELETE FROM "user"
                WHERE "id" = '%s';
                """;
        var query = String.format(queryFormat, uuid);
        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        var query = """
                CREATE TABLE IF NOT EXISTS "user" (
                    "id" varchar(38) PRIMARY KEY,
                    "firstName" varchar(100) NOT NULL,
                    "lastName" varchar(100) NOT NULL,
                    "email" varchar(250) NOT NULL,
                    "createdAt" INTEGER NOT NULL
                );
                """;
        var statement = getConnection().createStatement();
        statement.executeUpdate(query);
    }
}

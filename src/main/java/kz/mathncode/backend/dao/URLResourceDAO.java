package kz.mathncode.backend.dao;

import kz.mathncode.backend.entity.URLResource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class URLResourceDAO extends AbstractDAO<URLResource, UUID> {
    public URLResourceDAO(Connection connection, Class<URLResource> entityClass, Class<UUID> uuidClass) {
        super(connection, entityClass, uuidClass);
    }

    @Override
    public void create(URLResource entity) throws SQLException {

    }

    @Override
    public URLResource readById(UUID uuid) throws SQLException {
        return null;
    }

    @Override
    public void update(URLResource entity, UUID uuid) throws SQLException {

    }

    @Override
    public void deleteById(UUID uuid) throws SQLException {

    }

    @Override
    public void createTableIfNotExists() throws SQLException {

    }
}

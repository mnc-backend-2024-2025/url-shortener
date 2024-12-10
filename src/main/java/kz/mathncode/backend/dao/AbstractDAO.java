package kz.mathncode.backend.dao;

import java.sql.Connection;

public abstract class AbstractDAO<T, ID> implements DAO<T, ID> {
    private final Connection connection;
    private final Class<T> entityClass;
    private final Class<ID> idClass;

    public AbstractDAO(Connection connection, Class<T> entityClass, Class<ID> idClass) {
        this.connection = connection;
        this.entityClass = entityClass;
        this.idClass = idClass;
    }

    @Override
    public Class<ID> getIdClass() {
        return idClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    public Connection getConnection() {
        return connection;
    }
}

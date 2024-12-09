package kz.mathncode.backed.dao;

import java.sql.SQLException;

public interface DAO<T, ID> {
    void create(T entity);
    void readById(ID id );
    void update(T entity, ID id);
    void deleteById(ID id);
    void createTableIfNotExists() throws SQLException;
    Class<T> getEntityClass();
    Class<ID> getIdClass();
}

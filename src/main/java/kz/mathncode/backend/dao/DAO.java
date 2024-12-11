package kz.mathncode.backend.dao;

import java.sql.SQLException;


//DAO -- Data Access Object
public interface DAO<T, ID> {
    void create(T entity) throws SQLException;
    T readById(ID id ) throws SQLException;
    void update(T entity, ID id) throws SQLException;
    void deleteById(ID id) throws SQLException;
    void createTableIfNotExists() throws SQLException;
    Class<T> getEntityClass();
    Class<ID> getIdClass();
}

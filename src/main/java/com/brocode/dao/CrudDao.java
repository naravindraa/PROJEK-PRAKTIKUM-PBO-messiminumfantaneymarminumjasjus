package com.brocode.dao;
import java.util.List;
/** Kontrak DAO generik sebagai fondasi implementasi JDBC. */
public interface CrudDao<T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T entity);
    void update(T entity);
    void delete(ID id);
}

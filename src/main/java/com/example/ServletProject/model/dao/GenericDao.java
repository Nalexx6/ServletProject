package com.example.ServletProject.model.dao;

import java.sql.ResultSet;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    T findById(Long id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void delete(Long id);
}

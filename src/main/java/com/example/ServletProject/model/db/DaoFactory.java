package com.example.ServletProject.model.db;

import java.sql.ResultSet;
import java.util.List;

public interface DaoFactory<T> {

    T findById(Long id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void delete(int id);
    T mapObject(ResultSet rs);
}

package com.example.ServletProject.model.dao;

import java.util.List;

/**
 * Main interface for Dao Factory pattern
 * Defines main operations for all Dao objects
 */
public interface GenericDao<T> extends AutoCloseable {

    T findById(Long id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void delete(Long id);
}

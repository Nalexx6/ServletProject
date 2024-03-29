package com.example.ServletProject.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Defines general contract for mapping database result set rows to application entities.
 * Implementations are not supposed to move cursor of the resultSet via next() method,
 * but only extract information from the row in current cursor position.
 */
public interface ObjectMapper<T> {
    T mapObject(ResultSet rs) throws SQLException;
}

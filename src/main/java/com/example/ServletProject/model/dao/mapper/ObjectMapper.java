package com.example.ServletProject.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {

    T mapObject(ResultSet rs) throws SQLException;
}

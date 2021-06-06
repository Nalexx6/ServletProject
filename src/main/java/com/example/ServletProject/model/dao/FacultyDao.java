package com.example.ServletProject.model.dao;

import com.example.ServletProject.model.entity.Faculty;

public interface FacultyDao extends GenericDao<Faculty> {

    Faculty findByName(String name);
}

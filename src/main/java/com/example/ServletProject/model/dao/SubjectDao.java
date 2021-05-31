package com.example.ServletProject.model.dao;

import com.example.ServletProject.model.entity.Subject;

public interface SubjectDao extends GenericDao<Subject> {

    Subject findByName(String name);
}

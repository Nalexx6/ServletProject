package com.example.ServletProject.model.dao.impl;

import com.example.ServletProject.model.dao.DaoFactory;

/**
 * Dao Factory Implementation
 */
public class JDBCDaoFactory extends DaoFactory {

    @Override
    public JDBCFacultyDao createFacultyDao() {
        return new JDBCFacultyDao();
    }

    @Override
    public JDBCSubjectDao createSubjectDao() {
        return new JDBCSubjectDao();
    }

    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public JDBCSubmissionDao createSubmissionDao() {
        return new JDBCSubmissionDao();
    }
}

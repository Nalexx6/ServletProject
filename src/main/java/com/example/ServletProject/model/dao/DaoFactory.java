package com.example.ServletProject.model.dao;

import com.example.ServletProject.model.dao.impl.*;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract FacultyDao createFacultyDao();
    public abstract SubjectDao createSubjectDao();
    public abstract UserDao createUserDao();
    public abstract SubmissionDao createSubmissionDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

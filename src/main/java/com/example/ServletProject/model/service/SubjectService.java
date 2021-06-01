package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.SubjectDao;
import com.example.ServletProject.model.entity.Subject;

public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Subject findSubjectByName(String name){
        try(SubjectDao subjectDao = daoFactory.createSubjectDao()){
            return subjectDao.findByName(name);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

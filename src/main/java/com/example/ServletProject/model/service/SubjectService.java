package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.SubjectDao;
import com.example.ServletProject.model.entity.Subject;

import java.util.List;

/**
 * Service layer for Subject entity with all possible operations
 */
public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Subject getSubjectByName(String name){
        try(SubjectDao subjectDao = daoFactory.createSubjectDao()){
            return subjectDao.findByName(name);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Subject> getAllSubjects(){
        try(SubjectDao subjectDao = daoFactory.createSubjectDao()){
            return subjectDao.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

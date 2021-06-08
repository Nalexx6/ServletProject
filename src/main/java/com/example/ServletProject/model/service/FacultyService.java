package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.FacultyDao;
import com.example.ServletProject.model.dao.SubmissionDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;

import java.util.List;

/**
 * Service layer for Faculty entity with all possible operations
 */
public class FacultyService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Faculty> getAllFaculties() {
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            return facultyDao.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Faculty getFacultyByName(String name) {
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            return facultyDao.findByName(name);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Faculty getFacultyById(Long id) {
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            return facultyDao.findById(id);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void addFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.insert(faculty);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void editFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.update(faculty);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.delete(faculty.getId());
        } catch (Exception e){
            e.printStackTrace();
        }

        try (SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submissionDao.deleteAllForFaculty(faculty);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Submission> findAllSubmissionsForFaculty(Faculty faculty){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            return submissionDao.findAllForFaculty(faculty);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}

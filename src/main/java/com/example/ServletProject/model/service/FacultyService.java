package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.FacultyDao;
import com.example.ServletProject.model.entity.Faculty;

import java.util.List;

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

    public boolean addFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.insert(faculty);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean editFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.update(faculty);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteFaculty(Faculty faculty){
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            facultyDao.delete(faculty.getId());
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}

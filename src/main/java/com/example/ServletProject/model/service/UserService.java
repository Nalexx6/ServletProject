package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.FacultyDao;
import com.example.ServletProject.model.dao.SubmissionDao;
import com.example.ServletProject.model.dao.UserDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public User findUserByLogin(String login) {
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.findByLogin(login);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user){
        try(UserDao userDao = daoFactory.createUserDao()){
            userDao.insert(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean blockUser(User user) {
        try(UserDao userDao = daoFactory.createUserDao()){
            user.setRole("BLOCKED");
            userDao.update(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean unblockUser(User user) {
        try(UserDao userDao = daoFactory.createUserDao()){
            user.setRole("USER");
            userDao.update(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Faculty> getAllUnsubmittedFaculties(User user) {
        try(FacultyDao facultyDao = daoFactory.createFacultyDao()){
            List<Faculty> res = facultyDao.findAll();
            for(Submission s: user.getSubmissions()){
                res.remove(s.getFaculty());
            }
            return res;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Submission> findAllSubmissionsForUser(User user){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            return submissionDao.findAllForUser(user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

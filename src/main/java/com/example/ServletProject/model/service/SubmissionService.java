package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.SubmissionDao;
import com.example.ServletProject.model.entity.Submission;

import java.util.List;

public class SubmissionService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Submission> getAllSubmissions() {
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            return submissionDao.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean addSubmission(Submission submission){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submissionDao.insert(submission);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkSubmission(Submission submission){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submission.setChecked(true);
            submissionDao.update(submission);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}

package com.example.ServletProject.model.service;

import com.example.ServletProject.model.dao.DaoFactory;
import com.example.ServletProject.model.dao.SubmissionDao;
import com.example.ServletProject.model.entity.Submission;

import java.util.List;

/**
 * Service layer for Submission entity with all possible operations
 */
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

    public Submission getSubmissionById(Long id){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            return submissionDao.findById(id);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void addSubmission(Submission submission){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submissionDao.insert(submission);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void checkSubmission(Submission submission){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submission.setChecked(true);
            submissionDao.update(submission);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void finalizeSubmission(Submission submission, int status){
        try(SubmissionDao submissionDao = daoFactory.createSubmissionDao()){
            submission.setChecked(true);
            submission.setFinalizationStatus(status);
            submissionDao.update(submission);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

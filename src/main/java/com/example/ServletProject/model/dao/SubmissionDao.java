package com.example.ServletProject.model.dao;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import java.util.List;

public interface SubmissionDao extends GenericDao<Submission> {

    List<Submission> findAllForUser(User user);
    List<Submission> findAllForFaculty(Faculty faculty);
}

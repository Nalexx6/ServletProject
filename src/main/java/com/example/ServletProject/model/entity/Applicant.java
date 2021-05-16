package com.example.ServletProject.model.entity;

import java.util.List;
import java.util.Map;

public class Applicant extends User {

    private String city;
    private String institution;

    private Map<String, Integer> grades;
    private List<Faculty> submissions;
    private double secEducationAvg;

}

package com.example.ServletProject.model.entity;

import java.util.Map;

public class Faculty extends Entity{

    private String name;
    private int studentsAmount;
    private int stateFundedAmount;

    private Map<Subject, Double> subjectWeights;

    public Faculty(String name, int stateFundedAmount, int studentsAmount){
        this.name = name;
        this.stateFundedAmount = stateFundedAmount;
        this.studentsAmount = studentsAmount;
    }

    public Faculty(){

    }

    public int getStudentsAmount() {
        return studentsAmount;
    }

    public void setStudentsAmount(int studentsAmount) {
        this.studentsAmount = studentsAmount;
    }

    public int getStateFundedAmount() {
        return stateFundedAmount;
    }

    public void setStateFundedAmount(int stateFundedAmount) {
        this.stateFundedAmount = stateFundedAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Subject, Double> getSubjectWeights() {
        return subjectWeights;
    }

    public void setSubjectWeights(Map<Subject, Double> subjectWeights) {
        this.subjectWeights = subjectWeights;
    }
}

package com.example.ServletProject.model.entity;

import java.util.List;

public class Faculty extends Entity{

    private String name;
    private Integer studentsAmount;
    private Integer stateFundedAmount;

    private List<Subject> subjects;
    private List<Submission> submissions;

    public Faculty(String name, int stateFundedAmount, int studentsAmount){
        this.name = name;
        this.stateFundedAmount = stateFundedAmount;
        this.studentsAmount = studentsAmount;
    }

    public Faculty(){

    }

    public Integer getStudentsAmount() {
        return studentsAmount;
    }

    public void setStudentsAmount(Integer studentsAmount) {
        this.studentsAmount = studentsAmount;
    }

    public Integer getStateFundedAmount() {
        return stateFundedAmount;
    }

    public void setStateFundedAmount(Integer stateFundedAmount) {
        this.stateFundedAmount = stateFundedAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Faculty)){
            return false;
        }

        Faculty faculty = (Faculty) o;
        return name.equals(faculty.name) &&
                studentsAmount.equals(faculty.getStudentsAmount()) &&
                stateFundedAmount.equals(faculty.getStateFundedAmount()) &&
                subjects.get(0).equals(faculty.getSubjects().get(0)) &&
                subjects.get(1).equals(faculty.getSubjects().get(1)) &&
                subjects.get(2).equals(faculty.getSubjects().get(2));
    }

}

package com.example.ServletProject.model.entity;

import java.util.List;
import java.util.Map;

public class Faculty extends Entity{

    private String name;
    private int studentsAmount;
    private int stateFundedAmount;

    private List<Subject> subjects;

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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
                studentsAmount == faculty.studentsAmount &&
                stateFundedAmount == faculty.getStateFundedAmount() &&
                subjects.get(0).equals(faculty.subjects.get(0)) &&
                subjects.get(0).equals(faculty.subjects.get(1)) &&
                subjects.get(0).equals(faculty.subjects.get(2));
    }
}

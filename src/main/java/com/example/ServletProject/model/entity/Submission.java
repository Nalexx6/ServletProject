package com.example.ServletProject.model.entity;

import java.util.List;

public class Submission extends Entity {

    Faculty faculty;
    User user;
    List<Integer> grades;
    Double secEducAvg;
    boolean checked;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public Double getSecEducAvg() {
        return secEducAvg;
    }

    public void setSecEducAvg(Double secEducAvg) {
        this.secEducAvg = secEducAvg;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

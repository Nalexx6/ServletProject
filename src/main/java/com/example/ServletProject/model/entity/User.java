package com.example.ServletProject.model.entity;

import java.util.List;
import java.util.Map;

public class User extends com.example.ServletProject.model.entity.Entity {

    private String login;
    private String password;

    private String firstName;
    private String lastName;

    private String role;
    private String email;

    private String city;
    private String region;
    private String institution;

    private Map<String, Integer> grades;
    private List<Faculty> submissions;
    private double secEducationAvg;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<Faculty> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Faculty> submissions) {
        this.submissions = submissions;
    }

}

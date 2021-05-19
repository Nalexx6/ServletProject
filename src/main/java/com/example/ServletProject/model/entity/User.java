package com.example.ServletProject.model.entity;

import java.util.List;
import java.util.Map;

public class User extends com.example.ServletProject.model.entity.Entity {

    private String firstName;

    private String lastName;
    private boolean roleId;
    private String email;

    private String city;
    private String institution;

    private Map<String, Integer> grades;
    private List<Faculty> submissions;
    private double secEducationAvg;

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

    public boolean isRoleId() {
        return roleId;
    }

    public void setRoleId(boolean roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

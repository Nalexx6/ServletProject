package com.example.ServletProject.model.entity;

import com.example.ServletProject.model.db.DaoFactory;

import java.util.List;

public class Subject {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Subject)){
            return false;
        }

        Subject subject = (Subject) o;
        return name.equals(subject.name);
    }
}

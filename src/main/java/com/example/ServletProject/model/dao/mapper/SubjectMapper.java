package com.example.ServletProject.model.dao.mapper;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements ObjectMapper<Subject>{
    @Override
    public Subject mapObject(ResultSet rs){
        try {
            Subject subject = new Subject();
            subject.setId(rs.getLong(Fields.ENTITY__ID));
            subject.setName(rs.getString(Fields.SUBJECT__NAME));

            return subject;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}

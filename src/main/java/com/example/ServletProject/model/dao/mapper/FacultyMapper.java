package com.example.ServletProject.model.dao.mapper;

import com.example.ServletProject.model.dao.impl.JDBCSubjectDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyMapper implements ObjectMapper<Faculty>{

    @Override
    public Faculty mapObject(ResultSet rs) {
        try {
            Faculty faculty = new Faculty();

            faculty.setId(rs.getLong(Fields.ENTITY__ID));
            faculty.setName(rs.getString(Fields.FACULTY__NAME));
            faculty.setStudentsAmount(rs.getInt(Fields.FACULTY__STUDENT_AMOUNT));
            faculty.setStateFundedAmount(rs.getInt(Fields.FACULTY__STATE_FUNDED_AMOUNT));

            List<Subject> list = new ArrayList<>();
            JDBCSubjectDao sDao = new JDBCSubjectDao();
            list.add(sDao.findById(rs.getLong(Fields.FACULTY__SUB1_ID)));
            list.add(sDao.findById(rs.getLong(Fields.FACULTY__SUB2_ID)));
            list.add(sDao.findById(rs.getLong(Fields.FACULTY__SUB3_ID)));
            faculty.setSubjects(list);
            return faculty;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}

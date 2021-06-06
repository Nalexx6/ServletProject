package com.example.ServletProject.model.dao.mapper;

import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.dao.impl.JDBCUserDao;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubmissionMapper implements ObjectMapper<Submission> {

    @Override
    public Submission mapObject(ResultSet rs) {
        Submission submission = new Submission();
        try {
            submission.setId(rs.getLong(Fields.ENTITY__ID));
            JDBCUserDao uDao = new JDBCUserDao();
            submission.setUser(uDao.findById(rs.getLong(Fields.SUBMISSION__USER_ID)));
            JDBCFacultyDao fDao = new JDBCFacultyDao();
            submission.setFaculty(fDao.findById(rs.getLong(Fields.SUBMISSION__FACULTY_ID)));

            List<Integer> grades = new ArrayList<>();
            grades.add(rs.getInt(Fields.SUBMISSION__GRADE1));
            grades.add(rs.getInt(Fields.SUBMISSION__GRADE2));
            grades.add(rs.getInt(Fields.SUBMISSION__GRADE3));
            submission.setGrades(grades);

            submission.setSecEducAvg(rs.getDouble(Fields.SUBMISSION__SEC_EDUC_AVG));
            submission.setChecked(rs.getBoolean(Fields.SUBMISSION__CHECKED));
            submission.setFinalizationStatus(rs.getInt(Fields.SUBMISSION__FIN_STATUS));
            return submission;



        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}

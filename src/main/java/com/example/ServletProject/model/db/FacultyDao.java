package com.example.ServletProject.model.db;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyDao implements DaoFactory<Faculty> {


    @Override
    public Faculty findById(Long id) {
        return null;
    }

    public Faculty findByName(String name) {
        return null;
    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> res = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;


        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL.SQL__FIND_ALL_FAC);

            while (rs.next()) {
                res.add(mapObject(rs));
            }
        } catch (SQLException ex) {
            if(con != null){
                DBManager.getInstance().rollbackAndClose(con);
            }
            ex.printStackTrace();
        } finally {
            if(con != null){
                DBManager.getInstance().commitAndClose(con);
            }
        }

        return res;
    }

    @Override
    public void insert(Faculty faculty) {

    }

    @Override
    public void update(Faculty faculty) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Faculty mapObject(ResultSet rs) {
        try {
            Faculty faculty = new Faculty();

            faculty.setId(rs.getLong(Fields.ENTITY__ID));
            faculty.setName(rs.getString(Fields.FACULTY__NAME));
            faculty.setStudentsAmount(rs.getInt(Fields.FACULTY__STUDENT_AMOUNT));
            faculty.setStateFundedAmount(rs.getInt(Fields.FACULTY__STATE_FUNDED_AMOUNT));

            Map<Subject, Double> map = new HashMap<>();
            SubjectDao sDao = new SubjectDao();
            map.put(sDao.findById(rs.getLong(Fields.FACULTY__SUB1_ID)), rs.getDouble(Fields.FACULTY__WEIGHT1));
            map.put(sDao.findById(rs.getLong(Fields.FACULTY__SUB2_ID)), rs.getDouble(Fields.FACULTY__WEIGHT2));
            map.put(sDao.findById(rs.getLong(Fields.FACULTY__SUB3_ID)), rs.getDouble(Fields.FACULTY__WEIGHT3));
            faculty.setSubjectWeights(map);
            return faculty;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}

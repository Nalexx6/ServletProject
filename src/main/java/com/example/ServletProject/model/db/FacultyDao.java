package com.example.ServletProject.model.db;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.entity.User;

import java.sql.*;
import java.util.*;

public class FacultyDao implements DaoFactory<Faculty> {


    @Override
    public Faculty findById(Long id) {
        Faculty faculty = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_FAC_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                faculty = mapObject(rs);
            rs.close();
            pstmt.close();
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
        return faculty;
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
            System.out.println(res.size());
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
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int k = 1;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__INSERT_FAC, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(k++, faculty.getName());
            psmt.setLong(k++, faculty.getStudentsAmount());
            psmt.setLong(k++, faculty.getStateFundedAmount());

            SubjectDao sDao = new SubjectDao();

            for (Map.Entry<Subject, Double> entry : faculty.getSubjectWeights().entrySet()) {
                psmt.setLong(k, sDao.findByName(entry.getKey().getName()).getId());
                psmt.setDouble(k + 3, entry.getValue());
                k++;
            }

            if(psmt.executeUpdate() > 0) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    faculty.setId(rs.getLong(1));
                }
                rs.close();
            }
            psmt.close();
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
    }

    @Override
    public void update(Faculty faculty) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__UPDATE_FAC);
            psmt.setString(k++, faculty.getName());
            psmt.setLong(k++, faculty.getStudentsAmount());
            psmt.setLong(k++, faculty.getStateFundedAmount());

            for (Map.Entry<Subject, Double> entry : faculty.getSubjectWeights().entrySet()) {
                psmt.setLong(k, entry.getKey().getId());
                psmt.setDouble(k + 3, entry.getValue());
                k++;
            }

            psmt.executeUpdate();
            psmt.close();
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
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__DELETE_FAC);
            psmt.setLong(1, id);
            psmt.executeUpdate();
            psmt.close();
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

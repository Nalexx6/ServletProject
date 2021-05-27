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
            psmt.setString(k, faculty.getName());
            k++;
            psmt.setInt(k, faculty.getStudentsAmount());
            k++;
            psmt.setInt(k, faculty.getStateFundedAmount());
            k++;

            for (Subject s : faculty.getSubjects()) {
                psmt.setInt(k, (int) s.getId());
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

            for (Subject s: faculty.getSubjects()) {
                psmt.setLong(k, s.getId());
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

            List<Subject> list = new ArrayList<>();
            SubjectDao sDao = new SubjectDao();
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

package com.example.ServletProject.model.dao.impl;

import com.example.ServletProject.model.dao.DBManager;
import com.example.ServletProject.model.dao.FacultyDao;
import com.example.ServletProject.model.dao.SQL;
import com.example.ServletProject.model.dao.mapper.FacultyMapper;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Subject;

import java.sql.*;
import java.util.*;

public class JDBCFacultyDao implements FacultyDao {


    @Override
    public Faculty findById(Long id) {
        Faculty faculty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_FAC_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            FacultyMapper mapper = new FacultyMapper();
            if (rs.next())
                faculty = mapper.mapObject(rs);
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

    @Override
    public Faculty findByName(String name) {
        Faculty faculty = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_FAC_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            FacultyMapper mapper = new FacultyMapper();
            if (rs.next())
                faculty = mapper.mapObject(rs);
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

    @Override
    public List<Faculty> findAll() {
        List<Faculty> res = new ArrayList<>();

        Statement stmt;
        ResultSet rs;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL.SQL__FIND_ALL_FAC);

            FacultyMapper mapper = new FacultyMapper();
            while (rs.next()) {
                res.add(mapper.mapObject(rs));
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
        Connection con = null;
        PreparedStatement psmt;
        ResultSet rs;
        int k = 1;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__INSERT_FAC, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(k++, faculty.getName());
            psmt.setInt(k++, faculty.getStudentsAmount());
            psmt.setInt(k++, faculty.getStateFundedAmount());

            for (Subject s : faculty.getSubjects()) {
                psmt.setInt(k++, (int) s.getId());
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
        PreparedStatement psmt;
        int k = 1;
        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__UPDATE_FAC);
            psmt.setString(k++, faculty.getName());
            psmt.setLong(k++, faculty.getStudentsAmount());
            psmt.setLong(k++, faculty.getStateFundedAmount());

            for (Subject s: faculty.getSubjects()) {
                psmt.setLong(k++, s.getId());
            }

            psmt.setLong(k, faculty.getId());
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
    public void delete(Long id) {
        Connection con = null;
        PreparedStatement psmt;

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
    public void close() throws Exception {

    }
}

package com.example.ServletProject.model.dao.impl;

import com.example.ServletProject.model.dao.DBManager;
import com.example.ServletProject.model.dao.SQL;
import com.example.ServletProject.model.dao.SubjectDao;
import com.example.ServletProject.model.dao.mapper.SubjectMapper;
import com.example.ServletProject.model.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubjectDao implements SubjectDao {

    @Override
    public Subject findById(Long id) {
        Subject subject = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_SUBJECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            if (rs.next())
                subject = mapper.mapObject(rs);
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
        return subject;
    }

    @Override
    public Subject findByName(String name){
        Subject subject = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_SUBJECT_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            if (rs.next())
                subject = mapper.mapObject(rs);
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
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> res = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_ALL_SUBJECTS);
            rs = pstmt.executeQuery();
            SubjectMapper mapper = new SubjectMapper();
            while (rs.next()) {
                res.add(mapper.mapObject(rs));
            }
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
        return res;
    }

    @Override
    public void insert(Subject subject) {

    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public void close() throws Exception {

    }
}

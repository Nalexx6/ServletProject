package com.example.ServletProject.model.db;

import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectDao implements DaoFactory<Subject>{

    @Override
    public Subject findById(Long id) {
        Subject subject = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_SUBJECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                subject = mapObject(rs);
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

    public Subject findByName(String name){
        Subject subject = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_SUBJECT_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next())
                subject = mapObject(rs);
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
        return null;
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

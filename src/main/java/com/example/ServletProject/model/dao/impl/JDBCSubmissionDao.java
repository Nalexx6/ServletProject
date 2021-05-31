package com.example.ServletProject.model.dao.impl;

import com.example.ServletProject.model.dao.DBManager;
import com.example.ServletProject.model.dao.GenericDao;
import com.example.ServletProject.model.dao.SQL;
import com.example.ServletProject.model.dao.SubmissionDao;
import com.example.ServletProject.model.dao.mapper.SubmissionMapper;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSubmissionDao implements SubmissionDao {
    @Override
    public Submission findById(Long id) {
        return null;
    }

    @Override
    public List<Submission> findAll() {
        List<Submission> res = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL.SQL__FIND_ALL_SUBMISSIONS);

            SubmissionMapper mapper = new SubmissionMapper();
            while (rs.next()) {
                res.add(mapper.mapObject(rs));
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
    public List<Submission> findAllForUser(User user){
        List<Submission> res = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_ALL_SUBMISSIONS_FOR_USER);
            pstmt.setLong(1, user.getId());
            rs = pstmt.executeQuery();

            SubmissionMapper mapper = new SubmissionMapper();
            while (rs.next()) {
                res.add(mapper.mapObject(rs));
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
    public List<Submission> findAllForFaculty(Faculty faculty){
        List<Submission> res = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_ALL_SUBMISSIONS_FOR_FACULTY);
            pstmt.setLong(1, faculty.getId());
            rs = pstmt.executeQuery();

            SubmissionMapper mapper = new SubmissionMapper();
            while (rs.next()) {
                res.add(mapper.mapObject(rs));
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
    public void insert(Submission submission) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int k = 1;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__INSERT_SUBMISSION, Statement.RETURN_GENERATED_KEYS);
            psmt.setLong(k++, submission.getFaculty().getId());
            psmt.setLong(k++, submission.getUser().getId());

            for (Integer  g  : submission.getGrades()) {
                psmt.setInt(k++, g);
            }

            psmt.setDouble(k++, submission.getSecEducAvg());
            psmt.setBoolean(k, submission.isChecked());


            if(psmt.executeUpdate() > 0) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    submission.setId(rs.getLong(1));
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
    public void update(Submission submission) {
        Connection con = null;
        PreparedStatement psmt = null;
        int k = 1;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__UPDATE_SUBMISSION);
            psmt.setBoolean(k++, submission.isChecked());
            psmt.setLong(k, submission.getId());

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

    }

    @Override
    public void close() throws Exception {

    }
}
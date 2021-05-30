package com.example.ServletProject.model.db;

import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.entity.Submission;

import java.sql.*;
import java.util.List;

public class SubmissionDao implements DaoFactory<Submission> {
    @Override
    public Submission findById(Long id) {
        return null;
    }

    @Override
    public List<Submission> findAll() {
        return null;
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

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Submission mapObject(ResultSet rs) {
        return null;
    }
}
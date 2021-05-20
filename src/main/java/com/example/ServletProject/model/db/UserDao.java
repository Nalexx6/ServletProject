package com.example.ServletProject.model.db;

import com.example.ServletProject.controller.Servlet;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;


public class UserDao {
    private static final Logger log = Logger.getLogger(Servlet.class);

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL__FIND_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email=?";

    public User findUser(Long id){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapUser(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public User findUserByEmail(String email){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
//            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }


    private User mapUser(ResultSet rs){
        try {
            User user = new User();
            user.setId(rs.getLong(Fields.ENTITY__ID));
            user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
            user.setLastName(rs.getString(Fields.USER__LAST_NAME));
            user.setEmail(rs.getString(Fields.USER__EMAIL));
            user.setRoleId(rs.getString(Fields.USER__ROLE_ID));
            user.setCity(rs.getString(Fields.USER__CITY));
            user.setRegion(rs.getString(Fields.USER__REGION));
            user.setInstitution(rs.getString(Fields.USER__INSTITUTION));

            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

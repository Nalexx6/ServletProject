package com.example.ServletProject.model.dao.impl;

import com.example.ServletProject.model.dao.DBManager;
import com.example.ServletProject.model.dao.GenericDao;
import com.example.ServletProject.model.dao.SQL;
import com.example.ServletProject.model.dao.UserDao;
import com.example.ServletProject.model.dao.mapper.UserMapper;
import com.example.ServletProject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCUserDao implements UserDao {

    @Override
    public User findById(Long id){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next())
                user = mapper.mapObject(rs);
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
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> res = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;


        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL.SQL__FIND_ALL_USERS);

            UserMapper mapper = new UserMapper();
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
    public User findByLogin(String login){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()) {
                user = mapper.mapObject(rs);
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
        return user;
    }

    @Override
    public void insert(User user) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getLogin());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getFirstName());
            psmt.setString(4, user.getLastName());
            psmt.setString(5, user.getEmail());
            psmt.setString(6, user.getRole());
            psmt.setString(7, user.getCity());
            psmt.setString(8, user.getRegion());
            psmt.setString(9, user.getInstitution());

            if(psmt.executeUpdate() > 0) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
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
    public void update(User user) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.getInstance().getConnection();
            psmt = con.prepareStatement(SQL.SQL__UPDATE_USER);
            psmt.setString(1, user.getRole());
            psmt.setLong(2, user.getId());

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
        //Not necessary
    }

    @Override
    public void close() throws Exception {

    }
}

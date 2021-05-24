package com.example.ServletProject.model.db;

import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;


import javax.servlet.http.HttpServletRequest;
import java.sql.*;

//import org.apache.log4j.Logger;


public class UserDao {
//    private static final Logger log = Logger.getLogger(Servlet.class);

    public User findUser(Long id){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapUser(rs);
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

    public User findUserByLogin(String login){
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL.SQL__FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            if(con != null){
                DBManager.getInstance().rollbackAndClose(con);
            }
            ex.printStackTrace();
        } finally {
//            if(con != null){
//                DBManager.getInstance().commitAndClose(con);
//            }
        }
        return user;
    }

    public void insertUser(User user) {
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


    private User mapUser(ResultSet rs){
        try {
            User user = new User();
            user.setId(rs.getLong(Fields.ENTITY__ID));
            user.setLogin(rs.getString(Fields.USER__LOGIN));
            user.setPassword(rs.getString(Fields.USER__PASSWORD));
            user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
            user.setLastName(rs.getString(Fields.USER__LAST_NAME));
            user.setEmail(rs.getString(Fields.USER__EMAIL));
            user.setRole(rs.getString(Fields.USER__ROLE_ID));
            user.setCity(rs.getString(Fields.USER__CITY));
            user.setRegion(rs.getString(Fields.USER__REGION));
            user.setInstitution(rs.getString(Fields.USER__INSTITUTION));

            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}

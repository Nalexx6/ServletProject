package com.example.ServletProject.model.dao.mapper;

import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User mapObject(ResultSet rs) {
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
            e.printStackTrace();
        }

        return null;
    }
}

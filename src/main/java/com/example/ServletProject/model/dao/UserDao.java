package com.example.ServletProject.model.dao;

import com.example.ServletProject.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByLogin(String login);
}

package com.example.ServletProject.model.db;

public final class SQL {

    public static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    public static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    public static final String SQL__INSERT_USER =
            "INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

}

package com.example.ServletProject.model.db;

public final class SQL {

    //User queries
    public static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    public static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    public static final String SQL__INSERT_USER =
            "INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

//    public static final String SQL__UPDATE_USER =
//            "UPDATE users SET (login, password, first_name, last_name, email, role, city, region, institution)" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?";


    //////////////////////////////////////////////////////////
    //Faculty queries
    public static final String SQL__FIND_ALL_FAC =
            "SELECT * FROM faculties";
    public static final String SQL__FIND_FAC_BY_ID =
            "SELECT * FROM faculties WHERE id=?";

    public static final String SQL__FIND_FAC_BY_NAME =
            "SELECT * FROM faculties WHERE name=?";

    public static final String SQL__INSERT_FAC =
            "INSERT INTO faculties (name, studentsAmount, stateFundedAmount, subject1_id, subject2_id, subject3_id," +
                    "weight1, weight2, weight3)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


    //////////////////////////////////////////////////////////
    //Subject queries
    public static final String SQL__FIND_ALL_SUB =
            "SELECT * FROM subjects";

    public static final String SQL__FIND_SUB_BY_ID =
            "SELECT * FROM subjects WHERE id=?";

    public static final String SQL__FIND_SUB_BY_NAME =
            "SELECT * FROM subjects WHERE name=?";

    public static final String SQL__INSERT_SUB =
            "INSERT INTO subjects (name)" +
                    "VALUES (?)";

}

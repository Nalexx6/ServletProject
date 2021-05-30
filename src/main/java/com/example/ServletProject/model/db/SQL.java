package com.example.ServletProject.model.db;

public final class SQL {

    //User queries
    public static final String SQL__FIND_ALL_USERS =
            "SELECT * FROM users";

    public static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    public static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    public static final String SQL__INSERT_USER =
            "INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL__UPDATE_USER =
            "UPDATE users SET login = ?,  password = ?, first_name = ?, last_name = ?, " +
                    "email = ?, role = ?, city = ?, region = ?, institution = ? " +
                    "WHERE id = ?";


    //////////////////////////////////////////////////////////
    //Faculty queries
    public static final String SQL__FIND_ALL_FAC =
            "SELECT * FROM faculties";

    public static final String SQL__FIND_FAC_BY_ID =
            "SELECT * FROM faculties WHERE id=?";

    public static final String SQL__FIND_FAC_BY_NAME =
            "SELECT * FROM faculties WHERE name=?";

    public static final String SQL__INSERT_FAC =
            "INSERT INTO faculties (name, students_amount, state_funded_amount, subject1_id, subject2_id, subject3_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL__UPDATE_FAC =
            "UPDATE faculties SET name = ?,  students_amount = ?, state_funded_amount = ?," +
                    " subject1_id = ?, subject2_id = ?, subject3_id = ? " +
                    "WHERE id=?";

    public static final String SQL__DELETE_FAC =
            "DELETE FROM faculties WHERE id=?";


    //////////////////////////////////////////////////////////
    //Subject queries
    public static final String SQL__FIND_ALL_SUBJECTS =
            "SELECT * FROM subjects";

    public static final String SQL__FIND_SUBJECT_BY_ID =
            "SELECT * FROM subjects WHERE id=?";

    public static final String SQL__FIND_SUBJECT_BY_NAME =
            "SELECT * FROM subjects WHERE name=?";

    public static final String SQL__INSERT_SUBJECT =
            "INSERT INTO subjects (name)" +
                    "VALUES (?)";

    ////////////////////////////////////////////////////////
    //Submission queries
    public static final String SQL__FIND_ALL_SUBMISSIONS =
            "SELECT * FROM submissions";

    public static final String SQL__FIND_SUBMISSION_BY_ID =
            "SELECT * FROM submissions WHERE id=?";

    public static final String SQL__INSERT_SUBMISSION =
            "INSERT INTO submissions (faculty_id, user_id, grade1, grade2, grade3, sec_education_avg, checked)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL__UPDATE_SUBMISSION =
            "UPDATE submissions SET checked = ?" +
                    "WHERE id=?";



}

package com.example.ServletProject.model.validator;

public final class Regex {

    //User SignUp regex
    public static final String USER__LOGIN = "^[\\w]{4,}$";
    public static final String USER__PASSWORD = "^[\\w]{4,}$";
    public static final String USER__FIRST_NAME = "^[A-ZА-Я][a-zа-я]+$";
    public static final String USER__LAST_NAME = "^[A-ZА-Я][a-zа-я]+$";
    public static final String USER__EMAIL = "^[A-zА-я]+@[a-zа-я]+.com$";
    public static final String USER__CITY = "^[A-ZА-Я][a-zа-я]+$";
    public static final String USER__REGION = "^[A-ZА-Я][a-zа-я]+$";
    public static final String USER__INSTITUTION = "^[A-ZА-Я][a-zа-я]+.*$";

    //Submission Grades
    public static final String SUBMISSION_GRADE = "^[0-9]{1,2}$";
    public static final String SUBMISSION_SEC_EDUC_AVG = "^[0-9]{1,2}\\.?[1-9]?$";

    //Faculty parameters
    public static final String FACULTY__NAME = "\\b[A-ZА-Я][a-zа-я]+\\b";
    public static final String FACULTY_ST_AMOUNT = "^[0-9]{1,2}$";
}

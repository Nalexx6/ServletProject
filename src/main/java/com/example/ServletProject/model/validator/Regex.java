package com.example.ServletProject.model.validator;

public final class Regex {

    //User SignUp regex
    public static final String USER__LOGIN = "^[\\w]{4,}$";
    public static final String USER__PASSWORD = "^[\\w]{4,}$";
    public static final String USER__FIRST_NAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";
    public static final String USER__LAST_NAME = "^[A-ZЄІЇА-Я][a-zа-яёєії]+$";
    public static final String USER__EMAIL = "^[A-zЄІЇА-яёєії]+@[a-zа-яёєії]+.com$";
    public static final String USER__CITY = "^[A-zЄІЇА-яёєії]+$";
    public static final String USER__REGION = "^[A-zЄІЇА-яёєії]+$";
    public static final String USER__INSTITUTION = "^[A-zЄІЇА-яёєії]+.*$";

    //Submission Grades
    public static final String SUBMISSION_GRADE = "^[0-9]{1,2}$";
    public static final String SUBMISSION_SEC_EDUC_AVG = "^[0-9]{1,2}\\.?[1-9]?$";

    //Faculty parameters
    public static final String FACULTY__NAME = "[A-zЄІЇА-яёєії]+";
    public static final String FACULTY_ST_AMOUNT = "^[0-9]{1,3}$";
}

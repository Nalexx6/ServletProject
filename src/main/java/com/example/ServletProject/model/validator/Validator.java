package com.example.ServletProject.model.validator;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateUserFields(User user){
        return  Pattern.compile(Regex.USER__LOGIN).matcher(user.getLogin()).find() &&
                Pattern.compile(Regex.USER__PASSWORD).matcher(user.getPassword()).find() &&
                Pattern.compile(Regex.USER__FIRST_NAME).matcher(user.getFirstName()).find() &&
                Pattern.compile(Regex.USER__LAST_NAME).matcher(user.getLastName()).find() &&
                Pattern.compile(Regex.USER__EMAIL).matcher(user.getEmail()).find()&&
                Pattern.compile(Regex.USER__CITY).matcher(user.getCity()).find()&&
                Pattern.compile(Regex.USER__REGION).matcher(user.getRegion()).find()&&
                Pattern.compile(Regex.USER__INSTITUTION).matcher(user.getInstitution()).find();
    }

    public static boolean validateFacultyFields(Faculty faculty){
        return faculty != null &&
                faculty.getName().length() > 0 &&
                validateFacultyName(faculty.getName()) &&
                faculty.getStudentsAmount() != null &&
                Pattern.compile(Regex.FACULTY_ST_AMOUNT).matcher(faculty.getStudentsAmount().toString()).find() &&
                faculty.getStateFundedAmount() != null &&
                Pattern.compile(Regex.FACULTY_ST_AMOUNT).matcher(faculty.getStateFundedAmount().toString()).find() &&
                faculty.getStudentsAmount() > faculty.getStateFundedAmount() &&
                faculty.getSubjects().get(0) != null &&
                faculty.getSubjects().get(1) != null &&
                faculty.getSubjects().get(2) != null;
    }

    private static boolean validateFacultyName(String name){
        Matcher matcher = Pattern.compile(Regex.FACULTY__NAME).matcher(name);

        StringBuilder builder = new StringBuilder();
        while(matcher.find()){
            builder.append(" ").append(matcher.group());
        }
        builder.deleteCharAt(0);
        System.out.println(builder);
        return name.length() == builder.length();
    }

    public static boolean validateEditedFaculty(Faculty editedFaculty, Faculty faculty){
        return validateFacultyFields(editedFaculty) && !faculty.equals(editedFaculty);
    }

    public static boolean validateSubmissionFields(Submission submission){
        return  submission.getGrades().size() == 3 &&
                Pattern.compile(Regex.SUBMISSION_GRADE).matcher(submission.getGrades().get(0).toString()).find() &&
                Pattern.compile(Regex.SUBMISSION_GRADE).matcher(submission.getGrades().get(1).toString()).find() &&
                Pattern.compile(Regex.SUBMISSION_GRADE).matcher(submission.getGrades().get(2).toString()).find() &&
                Pattern.compile(Regex.SUBMISSION_SEC_EDUC_AVG).matcher(submission.getSecEducAvg().toString()).find();
    }
}

package com.example.ServletProject.controller.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.model.entity.*;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubjectService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

public class LoginCommand implements Command{
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    /**
        Comparator for sorting submissions after finalization
    */
    private static final Comparator<Submission> gradesComparator = ((submission1, submission2) -> {
        if((submission2.getGrades().get(0) + submission2.getGrades().get(1) + submission2.getGrades().get(2)) >
                (submission1.getGrades().get(0) + submission1.getGrades().get(1) + submission1.getGrades().get(2))) {
            return 1;
        } else if ((submission2.getGrades().get(0) + submission2.getGrades().get(1) + submission2.getGrades().get(2)) <
                (submission1.getGrades().get(0) + submission1.getGrades().get(1) + submission1.getGrades().get(2))){
            return -1;
        } else {
            return submission2.getId().compareTo(submission1.getId());
        }

    });

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        String login = request.getParameter(Fields.USER__LOGIN);

        if( login == null || login.equals("") ){
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid user parameters");
            return "/login/userLogin.jsp";
        }

        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);

        if(validateUserData(user, request)){
            if(user.getRole().equals("ADMIN")) {
                SubmissionService submissionService = new SubmissionService();
                FacultyService facultyService = new FacultyService();
                SubjectService subjectService = new SubjectService();

                //set corresponding submissions for each faculty
                List<Faculty> faculties = facultyService.getAllFaculties();
                for(Faculty f: faculties){
                    List<Submission> submissions = facultyService.findAllSubmissionsForFaculty(f);
                    submissions.sort(gradesComparator);
                    f.setSubmissions(submissions);
                }

                SessionUtil.setUserParams(request, user, faculties, userService.getAllUsers(), submissionService.getAllSubmissions(),
                        subjectService.getAllSubjects());
                log.debug("Logging in as ADMIN");
                log.debug("Command finished");
                return Paths.ADMIN_PAGE;
            } else if ((user.getRole().equals("BLOCKED")) ){
                request.getSession().setAttribute("message", MessageKeys.USER_BLOCKED);
                log.trace("This user is blocked");
                return "redirect:" + Paths.LOGIN_PAGE;
            } else {
                user.setSubmissions(userService.findAllSubmissionsForUser(user));
                SessionUtil.setUserParams(request, user, userService.getAllUnsubmittedFaculties(user));
                log.debug("Logging in as USER");
                log.debug("Command finished");
                return Paths.USER_PAGE;
            }
        } else {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            log.trace("Invalid user parameters");
            return "redirect:" + Paths.LOGIN_PAGE;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request){
        return  (user != null) &&
                (request.getParameter(Fields.USER__PASSWORD).equals(user.getPassword()));
    }
}

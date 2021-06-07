package com.example.ServletProject.controller.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class LoginCommand implements Command{

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

    static private void setUserRole(HttpServletRequest request,
                                    User user, List<Faculty> faculties, List<User> users, List<Submission> submissions) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("faculties", faculties);
        session.setAttribute("users", users);
        session.setAttribute("submissions", submissions);
    }

    static private void setUserRole(HttpServletRequest request,
                                    User user, List<Faculty> faculties) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        System.out.println("Logging in user");
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(Fields.USER__LOGIN);

        if( login == null || login.equals("") ){
            request.getSession().setAttribute("message", "Please enter valid login and password!");
            return "/login/userLogin.jsp";
        }
        System.out.println(login + " ");

        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);



        if(validateUserData(user, request)){
            System.out.println("User validated");
            if(user.getRole().equals("ADMIN")) {
                SubmissionService submissionService = new SubmissionService();
                FacultyService facultyService = new FacultyService();
                List<Faculty> faculties = facultyService.getAllFaculties();
                for(Faculty f: faculties){
                    List<Submission> submissions = facultyService.findAllSubmissionsForFaculty(f);
                    submissions.sort(gradesComparator);
                    f.setSubmissions(submissions);
                }
                setUserRole(request, user, faculties,
                        userService.getAllUsers(), submissionService.getAllSubmissions());
                return Paths.ADMIN_PAGE;
            } else if ((user.getRole().equals("BLOCKED")) ){
                request.getSession().setAttribute("message", MessageKeys.USER_BLOCKED);
                return "redirect:" + Paths.LOGIN_PAGE;
            } else {
                user.setSubmissions(userService.findAllSubmissionsForUser(user));
                setUserRole(request, user, userService.getAllUnsubmittedFaculties(user));
                return Paths.USER_PAGE;
            }
        } else {
            request.getSession().setAttribute("message", MessageKeys.LOGIN_INVALID);
            return "redirect:" + Paths.LOGIN_PAGE;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request){
        return  (user != null) &&
                (request.getParameter(Fields.USER__PASSWORD).equals(user.getPassword()));
    }
}

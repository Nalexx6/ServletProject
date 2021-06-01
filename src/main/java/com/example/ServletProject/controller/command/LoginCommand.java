package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.GenericDao;
import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.dao.impl.JDBCSubmissionDao;
import com.example.ServletProject.model.dao.impl.JDBCUserDao;
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
import java.util.HashSet;
import java.util.List;

public class LoginCommand implements Command{

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
        User user = userService.findUserByLogin(login);



        if(validateUserData(user, request)){
            System.out.println("User validated");
            if(user.getRole().equals("ADMIN")) {
                SubmissionService submissionService = new SubmissionService();
                FacultyService facultyService = new FacultyService();
                setUserRole(request, user, facultyService.getAllFaculties(),
                        userService.getAllUsers(), submissionService.getAllSubmissions());
                return /*redirect:*/"/login/adminRes.jsp";
            } else if ((user.getRole().equals("BLOCKED")) ){
                request.getSession().setAttribute("message", "This profile is BLOCKED, please contact support team");
                return "redirect:/login/userLogin.jsp";
            } else {
                user.setSubmissions(userService.findAllSubmissionsForUser(user));
                setUserRole(request, user, userService.getAllUnsubmittedFaculties(user));
                return /*redirect:*/"/login/userRes.jsp";
            }
        } else {
            request.getSession().setAttribute("message", "Please enter valid login and password!");
            return "redirect:/login/userLogin.jsp";
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request){
        return  (user != null) &&
                (request.getParameter(Fields.USER__PASSWORD).equals(user.getPassword()));
    }
}

package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.GenericDao;
import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.dao.impl.JDBCSubmissionDao;
import com.example.ServletProject.model.dao.impl.JDBCUserDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

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
            //System.out.println("Not");
            return "/login/userLogin.jsp";
        }
        System.out.println(login + " ");
        //System.out.println("Yes!");
        //todo: check user is already logged

        JDBCUserDao uDao = new JDBCUserDao();
        User user = uDao.findUserByLogin(login);

        JDBCSubmissionDao sDao = new JDBCSubmissionDao();
        GenericDao<Faculty> fDao = new JDBCFacultyDao();

        if(validateUserData(user, request)){
            System.out.println("User validated");
            if(user.getRole().equals("ADMIN")) {
                setUserRole(request, user, fDao.findAll(), uDao.findAll(), sDao.findAll());
                return /*redirect:*/"/login/adminRes.jsp";
            } else if ((user.getRole().equals("BLOCKED")) ){
                request.getSession().setAttribute("message", "This profile is BLOCKED, please contact support team");
                return "redirect:/login/userLogin.jsp";
            } else {
                user.setSubmissions(sDao.findAllForUser(user));
                setUserRole(request, user, fDao.findAll());
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

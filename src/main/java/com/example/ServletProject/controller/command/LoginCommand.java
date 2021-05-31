package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.DaoFactory;
import com.example.ServletProject.model.db.FacultyDao;
import com.example.ServletProject.model.db.SubmissionDao;
import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements Command{

    static private void setUserRole(HttpServletRequest request,
                                    User user, List<Faculty> faculties, List<User> users, List<Submission> submissions) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
        session.setAttribute("users", users);
        session.setAttribute("submissions", submissions);
    }

    static private void setUserRole(HttpServletRequest request,
                                    User user, List<Faculty> faculties) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
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

        UserDao uDao = new UserDao();
        User user = uDao.findUserByLogin(login);

        SubmissionDao sDao = new SubmissionDao();
        DaoFactory<Faculty> fDao = new FacultyDao();

        if(validateUserData(user, request)){
            System.out.println("User validated");
            if(user.getRole().equals("ADMIN")) {

                setUserRole(request, user, fDao.findAll(), uDao.findAll(), sDao.findAll());
                return /*redirect:*/"/login/adminRes.jsp";
            } else {
                user.setSubmissions(sDao.findAllForUser(user));
                setUserRole(request, user, fDao.findAll());
                return /*redirect:*/"/login/userRes.jsp";
            }
        } else {
            return "/login/userLogin.jsp";
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request){
        return  (user != null) &&
                (request.getParameter(Fields.USER__PASSWORD).equals(user.getPassword()));
    }
}

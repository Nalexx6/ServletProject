package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command{

    static private void setUserRole(HttpServletRequest request,
                                    User user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(Fields.USER__LOGIN);
//        String pass = request.getParameter();

        if( login == null || login.equals("") ){
            //System.out.println("Not");
            return null;
        }
        System.out.println(login + " ");
        //System.out.println("Yes!");
        //todo: check user is already logged

//        if(CommandUtility.checkUserIsLogged(request, email)){
//            return "/WEB-INF/error.jsp";
//        }
        UserDao dao = new UserDao();
        User user = dao.findUserByLogin(login);

        if(validateUserData(user, request)){
            setUserRole(request, user);
            System.out.println("User validated");
            if(user.getRole().equals("ADMIN")) {
                return /*redirect:*/"/login/adminRes.jsp";
            } else {
                return /*redirect:*/"/login/userRes.jsp";
            }
        } else {
            return null;
        }
    }

    private boolean validateUserData(User user, HttpServletRequest request){
        return  (user != null) &&
                (request.getParameter(Fields.USER__PASSWORD).equals(user.getPassword()));
    }
}

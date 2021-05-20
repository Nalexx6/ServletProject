package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command{

    static void setUserRole(HttpServletRequest request,
                            String role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("useremail", email);
        session.setAttribute("role", role);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
//        String pass = request.getParameter();

        if( email == null || email.equals("") ){
            //System.out.println("Not");
            return "/login.jsp";
        }
        System.out.println(email + " ");
        //System.out.println("Yes!");
        //todo: check login with DB

//        if(CommandUtility.checkUserIsLogged(request, email)){
//            return "/WEB-INF/error.jsp";
//        }
        UserDao dao = new UserDao();
        User user = dao.findUserByEmail(email);

        if(user != null){
            setUserRole(request, user.getRole(), email);
            return /*redirect:*/"/login/loginRes.jsp";
        } else {
            return "/index.jsp";
        }
    }
}

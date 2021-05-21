package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command{

    static private void setUserRole(HttpServletRequest request,
                                    User user, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("useremail", email);
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
//        String pass = request.getParameter();

        if( email == null || email.equals("") ){
            //System.out.println("Not");
            return null;
        }
        System.out.println(email + " ");
        //System.out.println("Yes!");
        //todo: check user is already logged

//        if(CommandUtility.checkUserIsLogged(request, email)){
//            return "/WEB-INF/error.jsp";
//        }
        UserDao dao = new UserDao();
        User user = dao.findUserByEmail(email);

        if(validateUserData(user, request)){
            setUserRole(request, user, email);
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
                (request.getParameter(Fields.USER__FIRST_NAME).equals(user.getFirstName())) &&
                (request.getParameter(Fields.USER__LAST_NAME).equals(user.getLastName())) &&
                (request.getParameter(Fields.USER__CITY).equals(user.getCity())) &&
                (request.getParameter(Fields.USER__REGION).equals(user.getRegion())) &&
                (request.getParameter(Fields.USER__INSTITUTION).equals(user.getInstitution()));
    }
}

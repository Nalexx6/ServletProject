package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpCommand implements Command{

    static private void setUserRole(HttpServletRequest request,
                                    User user) {
        HttpSession session = request.getSession();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = UserDao.mapUser(request);
//        String pass = request.getParameter();
        UserDao dao = new UserDao();
        if(!validateUserData(user) || dao.findUserByEmail(user.getEmail()) != null){
            System.out.println("kfdkfld");
            return null;
        }
        //System.out.println("Yes!");
        //todo: check user is already logged

//        if(CommandUtility.checkUserIsLogged(request, email)){
//            return "/WEB-INF/error.jsp";
//        }
        dao.insertUser(user);

        setUserRole(request, user);
        if(user.getRole().equals("ADMIN")) {
            return /*redirect:*/"/login/adminRes.jsp";
        } else {
            return /*redirect:*/"/login/userRes.jsp";
        }
    }

    private boolean validateUserData(User user){
        return Pattern.compile("^[A-Z][a-z]+$").matcher(user.getFirstName()).find() &&
                Pattern.compile("^[A-Z][a-z]+$").matcher(user.getLastName()).find() &&
                Pattern.compile("^[A-Z][a-z]+$").matcher(user.getCity()).find();
    }
}

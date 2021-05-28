package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.UserDao;
import com.example.ServletProject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UnblockUserCommand implements Command{


    static private  void setUsers(HttpServletRequest request, List<User> users){
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int index = Integer.parseInt(request.getParameter("opIndex"));

        UserDao sDao = new UserDao();
        List<User> users = sDao.findAll();
        User user = users.get(index);
        user.setRole("USER");

        sDao.update(user);
        setUsers(request, sDao.findAll());

        return "/login/adminRes.jsp";
    }
}

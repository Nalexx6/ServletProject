package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCUserDao;
import com.example.ServletProject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BlockUserCommand implements Command{

    static private  void setUsers(HttpServletRequest request, List<User> users){
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int index = Integer.parseInt(request.getParameter("opIndex"));

        JDBCUserDao sDao = new JDBCUserDao();
        List<User> users = sDao.findAll();
        User user = users.get(index);
        user.setRole("BLOCKED");

        sDao.update(user);
        setUsers(request, sDao.findAll());

        return "redirect:/login/adminRes.jsp";
    }
}

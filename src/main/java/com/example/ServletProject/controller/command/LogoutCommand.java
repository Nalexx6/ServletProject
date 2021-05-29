package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        ServletContext context = request.getServletContext();
        HashSet<User> loggedUsers = (HashSet<User>) context.getAttribute("loggedUsers");
        loggedUsers.remove((User) session.getAttribute("user"));
        context.setAttribute("loggedUsers", loggedUsers);

        if(session != null){
            session.invalidate();
        }

        return "/index.jsp";
    }
}

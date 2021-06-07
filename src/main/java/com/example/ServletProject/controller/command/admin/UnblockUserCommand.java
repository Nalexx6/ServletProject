package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UnblockUserCommand implements Command {
    static private  void setUsers(HttpServletRequest request, List<User> users){
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        userService.unblockUser(userService.getUserById(Long.parseLong(request.getParameter("opIndex"))));

        setUsers(request, userService.getAllUsers());

        return "redirect:" + Paths.ADMIN_PAGE;
    }
}

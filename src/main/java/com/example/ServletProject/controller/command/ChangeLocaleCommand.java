package com.example.ServletProject.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("locale", request.getParameter("locale").equals("EN") ? "en" : "ru");
        return "redirect:"+request.getParameter("page-path");
    }
}

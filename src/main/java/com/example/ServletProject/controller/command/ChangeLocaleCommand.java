package com.example.ServletProject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command{
    private static final Logger log = LogManager.getLogger(ChangeLocaleCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        request.getSession().setAttribute("locale", request.getParameter("locale").equals("EN") ? "en" : "ru");

        log.debug("Command finished");
        return "redirect:"+request.getParameter("page-path");
    }
}

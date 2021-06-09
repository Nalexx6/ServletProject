package com.example.ServletProject.controller.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class LogoutCommand implements Command{
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command stats");

        HttpSession session = request.getSession(false);

        //remove current user from logged list
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        if(session != null) {
            loggedUsers.remove(((User) session.getAttribute("user")).getLogin());
            context.setAttribute("loggedUsers", loggedUsers);
            session.invalidate();
        }



        log.debug("Command finished");
        return "redirect:" + Paths.MAIN_PAGE;
    }
}

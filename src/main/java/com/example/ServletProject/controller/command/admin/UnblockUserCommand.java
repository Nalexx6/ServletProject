package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.SessionUtil;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UnblockUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(UnblockUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        UserService userService = new UserService();
        userService.unblockUser(userService.getUserById(Long.parseLong(request.getParameter("opIndex"))));

        SessionUtil.setUsers(request, userService.getAllUsers());

        log.debug("Command finished");
        return "redirect:" + Paths.ADMIN_PAGE;
    }
}

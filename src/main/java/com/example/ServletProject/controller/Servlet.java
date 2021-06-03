package com.example.ServletProject.controller;

import com.example.ServletProject.controller.command.*;
import com.example.ServletProject.controller.command.admin.*;
import com.example.ServletProject.controller.command.user.CreateSubmissionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();
    private static final Logger log = LogManager.getLogger(Servlet.class);

    @Override
    public void init(){

        this.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login",
                new LoginCommand());
        commands.put("signUp",
                new SignUpCommand());
        commands.put("logout",
                new LogoutCommand());
        commands.put("createFaculty",
                new CreateFacultyCommand());
        commands.put("deleteFaculty",
                new DeleteFacultyCommand());
        commands.put("editFaculty",
                new EditFacultyCommand());
        commands.put("blockUser",
                new BlockUserCommand());
        commands.put("unblockUser",
                new UnblockUserCommand());
        commands.put("createSubmission",
                new CreateSubmissionCommand());
        commands.put("checkSubmission",
                new CheckSubmissionCommand());
        commands.put("changeLocale",
                new ChangeLocaleCommand());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("Controller starts");

        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);


        Command command = commands.getOrDefault(commandName, (r)->Paths.MAIN_PAGE);
        log.trace("Obtained command --> " + command);
        System.out.println(command);

        String page = command.execute(request);

        if(page.contains("redirect:")){
            log.trace("Redirect address --> " + page);
            log.debug("Controller finished, now go to address --> " + page);
            response.sendRedirect(page.replace("redirect:", request.getContextPath()));
        }else {
            log.trace("Forward address --> " + page);
            log.debug("Controller finished, now go to forward address --> " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void destroy() {
    }
}
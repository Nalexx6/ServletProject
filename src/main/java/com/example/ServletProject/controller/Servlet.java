package com.example.ServletProject.controller;

import com.example.ServletProject.controller.command.*;
import com.example.ServletProject.controller.command.admin.*;
import com.example.ServletProject.controller.command.user.CreateSubmissionCommand;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public Servlet(){

    }

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

        String commandName = request.getParameter("command");
        Command command = commands.getOrDefault(commandName, (r)->"/index.jsp");

        System.out.println(command.getClass().getName());
        String page = command.execute(request);

        System.out.println(page);

        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", request.getContextPath()));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void destroy() {
    }
}
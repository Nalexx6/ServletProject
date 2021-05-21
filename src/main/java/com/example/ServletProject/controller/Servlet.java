package com.example.ServletProject.controller;

import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.LoginCommand;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public Servlet(){

    }

    public void init(){

//        servletConfig.getServletContext()
//                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login",
                new LoginCommand());
//        commands.put("exception" , new ExceptionCommand());
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
        //request.getRequestDispatcher(page).forward(request,response);
        if(page == null){
            //todo: create response in userLogin page
        }else if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/coffee"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void destroy() {
    }
}
package com.example.ServletProject.controller.filters;


import com.example.ServletProject.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                            throws IOException, ServletException {

        System.out.println("Filter is processing");

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final User user = (User) req.getSession().getAttribute("user");
        final String login = req.getParameter("login");
        ServletContext context = servletRequest.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");

        if(user == null && login != null ) {
            if (loggedUsers.contains(login)){
                String message = "User is already logged";
                servletRequest.setAttribute("error-message", message);
                servletRequest.getRequestDispatcher("/error-page.jsp")
                        .forward(servletRequest, servletResponse);
                System.out.println("user " + req.getParameter("login") + " is  already logged");
            } else{
                System.out.println("Logging in user");
                loggedUsers.add(login);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
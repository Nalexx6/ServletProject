package com.example.ServletProject.controller.filters;


import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;

@WebFilter(filterName = "auth")
public class AuthFilter implements Filter {

    private static final Logger log = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                            throws IOException, ServletException {
        log.debug("Filter starts");
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final User user = (User) req.getSession().getAttribute("user");
        final String login = req.getParameter("login");
        ServletContext context = servletRequest.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");

        //If user already logged on another browser or device, refuses to logging in him twice
        if(user == null && login != null ) {
            if (loggedUsers.contains(login)){
                String message = "User is already logged";
                servletRequest.setAttribute("message", message);
                servletRequest.getRequestDispatcher(Paths.MAIN_PAGE)
                        .forward(servletRequest, servletResponse);
                log.trace(message);
            }
        }

        //Prevent error message from displaying after page refreshing
        if(req.getSession().getAttribute("message-displayed") != null &&
                (boolean) req.getSession().getAttribute("message-displayed")) {
            req.getSession().removeAttribute("message");
        } else {
            req.getSession().setAttribute("message-displayed", true);
        }

        //For USER if sort was last command, div with accessible faculties will be displayed
        if(req.getSession().getAttribute("sortDisplayed") != null &&
                (int) req.getSession().getAttribute("sortDisplayed") == 0) {
            req.getSession().setAttribute("sortDisplayed", 1);
        } else {
            req.getSession().setAttribute("sortDisplayed", 2);
        }

        //Set default locale if user has not been on site yet
        if(req.getSession().getAttribute("locale") == null){
            req.getSession().setAttribute("locale", "EN");
        }

        //Set default sort parameters if user has not been on site yet
        if(req.getSession().getAttribute("alphabetSort") == null){
            req.getSession().setAttribute("alphabetSort", 1);
            req.getSession().setAttribute("studentSort", 3);
            req.getSession().setAttribute("stateFundedSort", 5);
        }

        log.debug("Filter finished");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

package com.example.ServletProject.controller.filters;

import com.example.ServletProject.controller.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter (filterName = "command-access")
public class CommandAccessFilter implements Filter {

    private static final Logger log = LogManager.getLogger(CommandAccessFilter.class);

    // commands access
    private static Map<String, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

        accessMap.put("ADMIN", Arrays.asList("createFaculty", "editFaculty", "deleteFaculty",
                "blockUser", "unblockUser", "checkSubmission"));

        accessMap.put("USER", Collections.singletonList("createSubmission"));

        commons = Arrays.asList("login", "signUp", "logout", "changeLocale", "sortFaculties");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter starts");
        System.out.println("CommandFilter is processing");

        if (accessAllowed(request)) {
            log.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessasge = "You do not have permission to access the requested resource";

            request.setAttribute("message", errorMessasge);
            log.trace("Set the request attribute: message --> " + errorMessasge);

            request.getRequestDispatcher(Paths.MAIN_PAGE)
                    .forward(request, response);
        }

    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null)
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        String userRole = (String) session.getAttribute("userRole");
        if (userRole == null)
            return false;

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

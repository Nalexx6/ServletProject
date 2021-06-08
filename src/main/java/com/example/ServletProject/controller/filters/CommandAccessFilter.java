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
    private static final Map<String, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> unableAfterFinalization = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

        accessMap.put("ADMIN", Arrays.asList("createFaculty", "editFaculty", "deleteFaculty",
                "blockUser", "unblockUser", "checkSubmission", "finaliseCertificate"));

        accessMap.put("USER", Collections.singletonList("createSubmission"));

        commons = Arrays.asList("login", "signUp", "logout", "changeLocale", "sortFaculties");

        unableAfterFinalization = Arrays.asList("signUp", "checkSubmission", "createFaculty", "editFaculty", "deleteFaculty",
                "blockUser", "unblockUser", "checkSubmission", "createSubmission", "finaliseCertificate");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter starts");

        if (accessAllowed(request)) {
            log.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("message", errorMessage);
            log.trace("Set the request attribute: message --> " + errorMessage);

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

        if((boolean) session.getServletContext().getAttribute("finalized") &&
                unableAfterFinalization.contains(commandName)){
            return false;
        }

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

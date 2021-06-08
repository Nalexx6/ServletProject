package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.SubmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckSubmissionCommand implements Command {

    private static final Logger log = LogManager.getLogger(CheckSubmissionCommand.class);


    static private void setSubmissions(HttpServletRequest request, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("submissions", submissions);
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        SubmissionService service = new SubmissionService();
        service.checkSubmission(service.getSubmissionById(Long.parseLong(request.getParameter("opIndex"))));

        setSubmissions(request, service.getAllSubmissions());

        log.debug("Command finished");
        return "redirect:" + Paths.ADMIN_PAGE;
    }
}

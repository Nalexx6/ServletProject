package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.SubmissionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckSubmissionCommand implements Command {
    static private void setSubmissions(HttpServletRequest request, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("submissions", submissions);
    }

    @Override
    public String execute(HttpServletRequest request) {
        SubmissionService service = new SubmissionService();
        service.checkSubmission(service.getAllSubmissions().get(Integer.parseInt(request.getParameter("opIndex"))));

        setSubmissions(request, service.getAllSubmissions());
        return "redirect:" + Paths.ADMIN_PAGE;
    }
}

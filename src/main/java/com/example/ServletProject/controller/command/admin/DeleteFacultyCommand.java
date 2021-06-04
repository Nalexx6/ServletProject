package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteFacultyCommand implements Command {
    static private  void setFaculties(HttpServletRequest request, List<Faculty> faculties, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
        session.setAttribute("submissions", submissions);
    }

    @Override
    public String execute(HttpServletRequest request) {
        FacultyService service = new FacultyService();
        service.deleteFaculty(service.getFacultyById(Long.parseLong(request.getParameter("opIndex"))));

        SubmissionService submissionService = new SubmissionService();
        setFaculties(request, service.getAllFaculties(), submissionService.getAllSubmissions());

        return "redirect:" + Paths.ADMIN_PAGE;
    }


}

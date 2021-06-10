package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.SessionUtil;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteFacultyCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        FacultyService service = new FacultyService();
        service.deleteFaculty(service.getFacultyById(Long.parseLong(request.getParameter("opIndex"))));

        SessionUtil.setFaculties(request);
        SessionUtil.setSubmissions(request);

        log.debug("Command finished");
        return "redirect:" + Paths.ADMIN_PAGE;
    }

}

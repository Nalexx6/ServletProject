package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.MessageKeys;
import com.example.ServletProject.controller.command.SessionUtil;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditFacultyCommand  implements Command {
    private static final Logger log = LogManager.getLogger(EditFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        Faculty editedFaculty = CreateFacultyCommand.mapFaculty(request);

        FacultyService service = new FacultyService();
        Faculty faculty = service.getFacultyById(Long.parseLong(request.getParameter("editedFacIndex")));
        System.out.println(faculty.getName());
        editedFaculty.setId(faculty.getId());
        if(!Validator.validateEditedFaculty(editedFaculty, faculty)){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_INVALID_EDITION);
            request.getSession().setAttribute("facIndex", editedFaculty.getId());
            log.trace("Invalid edition parameters");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        if(service.getFacultyByName(editedFaculty.getName()) != null && !editedFaculty.getName().equals(faculty.getName())){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_EXISTS);
            request.getSession().setAttribute("facIndex", editedFaculty.getId());
            log.trace("Faculty with such name already exists");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        service.editFaculty(editedFaculty);

        SessionUtil.setFaculties(request);
        SessionUtil.setSubmissions(request);


        log.debug("Command finished");
        return "redirect:" + Paths.ADMIN_PAGE;
    }

}

package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.MessageKeys;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class EditFacultyCommand  implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Faculty editedFaculty = CreateFacultyCommand.mapFaculty(request);

        FacultyService service = new FacultyService();
        Faculty faculty = service.getFacultyById(Long.parseLong(request.getParameter("editedFacIndex")));
        System.out.println(faculty.getName());
        editedFaculty.setId(faculty.getId());
        if(!Validator.validateEditedFaculty(editedFaculty, faculty)){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_INVALID_EDITION);
            request.getSession().setAttribute("facIndex", editedFaculty.getId());
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        if(service.getFacultyByName(editedFaculty.getName()) != null && !editedFaculty.getName().equals(faculty.getName())){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_EXISTS);
            request.getSession().setAttribute("facIndex", editedFaculty.getId());
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        service.editFaculty(editedFaculty);
        CreateFacultyCommand.setFaculties(request, service.getAllFaculties());
        return "redirect:" + Paths.ADMIN_PAGE;
    }

}

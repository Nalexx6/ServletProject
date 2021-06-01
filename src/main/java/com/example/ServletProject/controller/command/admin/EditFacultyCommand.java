package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.admin.CreateFacultyCommand;
import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditFacultyCommand  implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Faculty editedFaculty = CreateFacultyCommand.mapFaculty(request);

        FacultyService service = new FacultyService();
        Faculty faculty = service.getAllFaculties().get(Integer.parseInt(request.getParameter("editedFacIndex")));
        editedFaculty.setId(faculty.getId());
        if(!validateEdition(editedFaculty, faculty) || service.getFacultyByName(editedFaculty.getName()) != null){
            System.out.println("Invalid parameters of edition");
            return "redirect:/login/adminRes.jsp";
        }

        service.editFaculty(editedFaculty);
        CreateFacultyCommand.setFaculties(request, service.getAllFaculties());
        return "redirect:/login/adminRes.jsp";
    }

    private boolean validateEdition(Faculty editedFaculty, Faculty faculty){
        return !editedFaculty.equals(faculty) &&
                CreateFacultyCommand.validateFaculty(faculty);
    }

}

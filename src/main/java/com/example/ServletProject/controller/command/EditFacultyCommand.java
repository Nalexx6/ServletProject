package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditFacultyCommand  implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        Faculty editedFaculty = CreateFacultyCommand.mapFaculty(request);

        int index = Integer.parseInt(request.getParameter("editedFacIndex"));
        JDBCFacultyDao fDao = new JDBCFacultyDao();
        List<Faculty> faculties = fDao.findAll();
        Faculty faculty = faculties.get(index);
        editedFaculty.setId(faculty.getId());
        if(!validateEdition(editedFaculty, faculty) || fDao.findByName(editedFaculty.getName()) != null){
            System.out.println("Invalid parameters of edition");
            return "redirect:/login/adminRes.jsp";
        }

        fDao.update(editedFaculty);
        faculties = fDao.findAll();
        CreateFacultyCommand.setFaculties(request, faculties);
        return "redirect:/login/adminRes.jsp";
    }

    private boolean validateEdition(Faculty editedFaculty, Faculty faculty){
        return !editedFaculty.equals(faculty) &&
                CreateFacultyCommand.validateFaculty(faculty);
    }

}

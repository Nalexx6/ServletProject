package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteFacultyCommand implements Command {
    static private  void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        FacultyService service = new FacultyService();
        service.deleteFaculty(service.getAllFaculties().get(Integer.parseInt(request.getParameter("opIndex"))));

        setFaculties(request, service.getAllFaculties());

        return "redirect:/login/adminRes.jsp";
    }


}

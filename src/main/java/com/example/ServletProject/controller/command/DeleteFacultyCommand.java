package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.entity.Faculty;

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
        int index = Integer.parseInt(request.getParameter("opIndex"));

        JDBCFacultyDao fDao = new JDBCFacultyDao();
        List<Faculty> faculties = fDao.findAll();
        Faculty faculty = faculties.get(index);
        fDao.delete(faculty.getId());
        setFaculties(request, fDao.findAll());

        return "redirect:/login/adminRes.jsp";
    }


}

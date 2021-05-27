package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.db.FacultyDao;
import com.example.ServletProject.model.db.SubjectDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateFacultyCommand implements Command{

    static private  void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Faculty faculty = mapFaculty(request);

        FacultyDao fDao = new FacultyDao();
        if(!validateFaculty(faculty) || fDao.findByName(faculty.getName()) != null){
            System.out.println("faculty invalid or already exists");
            return "/login/adminRes.jsp";
        }

        fDao.insert(faculty);
        setFaculties(request, fDao.findAll());

        return "/login/adminRes.jsp";
    }

    public Faculty mapFaculty(HttpServletRequest request) {
        Faculty faculty = new Faculty();

        faculty.setName(request.getParameter(Fields.FACULTY__NAME));
        faculty.setStudentsAmount(Integer.parseInt(request.getParameter(Fields.FACULTY__STUDENT_AMOUNT)));
        faculty.setStateFundedAmount(Integer.parseInt(request.getParameter(Fields.FACULTY__STATE_FUNDED_AMOUNT)));

        List<Subject> list = new ArrayList<>();
        SubjectDao sDao = new SubjectDao();
        list.add(sDao.findByName(request.getParameter(Fields.FACULTY__SUB1_ID)));

        list.add(sDao.findByName(request.getParameter(Fields.FACULTY__SUB2_ID)));
        list.add(sDao.findByName(request.getParameter(Fields.FACULTY__SUB3_ID)));
        faculty.setSubjects(list);
        return faculty;

    }

    boolean validateFaculty(Faculty faculty){
        return faculty != null &&
                faculty.getName().length() > 0 &&
                faculty.getStudentsAmount() > faculty.getStateFundedAmount() &&
                faculty.getSubjects().get(0) != null &&
                faculty.getSubjects().get(1) != null &&
                faculty.getSubjects().get(2) != null;
    }
}

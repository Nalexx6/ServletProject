package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubjectService;
import com.example.ServletProject.model.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateFacultyCommand implements Command {
    static public void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Faculty faculty = mapFaculty(request);

        FacultyService service = new FacultyService();
        if(!Validator.validateFacultyFields(faculty)){
            request.getSession().setAttribute("message", "Parameters of new faculty must be valid");
            request.getSession().removeAttribute("facIndex");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        if(service.getFacultyByName(faculty.getName()) != null){
            request.getSession().setAttribute("message", "Faculty with such name already exists");
            request.getSession().removeAttribute("facIndex");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        service.addFaculty(faculty);
        setFaculties(request, service.getAllFaculties());

        return "redirect:" + Paths.ADMIN_PAGE;
    }

    public static Faculty mapFaculty(HttpServletRequest request) {
        Faculty faculty = new Faculty();

        faculty.setName(request.getParameter(Fields.FACULTY__NAME));
        try {
            faculty.setStudentsAmount(Integer.parseInt(request.getParameter(Fields.FACULTY__STUDENT_AMOUNT)));
            faculty.setStateFundedAmount(Integer.parseInt(request.getParameter(Fields.FACULTY__STATE_FUNDED_AMOUNT)));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        List<Subject> list = new ArrayList<>();
        SubjectService service = new SubjectService();
        list.add(service.findSubjectByName(request.getParameter(Fields.FACULTY__SUB1_ID)));

        list.add(service.findSubjectByName(request.getParameter(Fields.FACULTY__SUB2_ID)));
        list.add(service.findSubjectByName(request.getParameter(Fields.FACULTY__SUB3_ID)));
        faculty.setSubjects(list);
        return faculty;

    }

}

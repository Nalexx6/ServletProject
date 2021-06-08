package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.MessageKeys;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubjectService;
import com.example.ServletProject.model.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateFacultyCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateFacultyCommand.class);

    static public void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        Faculty faculty = mapFaculty(request);

        FacultyService service = new FacultyService();
        if(!Validator.validateFacultyFields(faculty)){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_INVALID);
            request.getSession().removeAttribute("facIndex");
            log.trace("Invalid faculty parameters");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        if(service.getFacultyByName(faculty.getName()) != null){
            request.getSession().setAttribute("message", MessageKeys.FACULTY_EXISTS);
            request.getSession().removeAttribute("facIndex");
            log.trace("Faculty with such name already exists");
            return "redirect:" + Paths.ADMIN_PAGE;
        }

        service.addFaculty(faculty);
        setFaculties(request, service.getAllFaculties());

        log.debug("Command finished");
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

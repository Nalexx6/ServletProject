package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortFacultiesCommand implements Command{

    private static final Comparator<Faculty> alphabetAscending = (Comparator.comparing(Faculty::getName));

    private static final Comparator<Faculty> alphabetDescending = ((faculty1, faculty2) ->
            faculty2.getName().compareTo(faculty1.getName()));

    private static final Comparator<Faculty> studentAmountAscending = (Comparator.comparing(Faculty::getStudentsAmount));

    private static final Comparator<Faculty> studentAmountDescending = ((faculty1, faculty2) ->
            faculty2.getStudentsAmount().compareTo(faculty1.getStudentsAmount()));

    private static final Comparator<Faculty> stateFundedAmountAscending = (Comparator.comparing(Faculty::getStateFundedAmount));

    private static final Comparator<Faculty> stateFundedAmountDescending = ((faculty1, faculty2) ->
            faculty2.getStateFundedAmount().compareTo(faculty1.getStateFundedAmount()));

    static public void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {

        int type = Integer.parseInt(request.getParameter("sortType"));

        List<Faculty> faculties = null;
        if("ADMIN".equals(request.getSession().getAttribute("role"))) {
            FacultyService service = new FacultyService();
            faculties = service.getAllFaculties();
        } else {
            UserService service = new UserService();
            faculties = service.getAllUnsubmittedFaculties((User) request.getSession().getAttribute("user"));
            request.getSession().setAttribute("sortDisplayed", 0);
        }
        sort(faculties, type, request);
        setFaculties(request, faculties);

        return "redirect:"+request.getParameter("page-path");
    }

    private void sort(List<Faculty> faculties, int type, HttpServletRequest request){

        switch (type) {
            case (1):
                faculties.sort(alphabetAscending);
                request.getSession().setAttribute("alphabetSort", 2);
                break;
            case (2):
                faculties.sort(alphabetDescending);
                request.getSession().setAttribute("alphabetSort", 1);
                break;
            case (3):
                faculties.sort(studentAmountAscending);
                request.getSession().setAttribute("studentSort", 4);
                break;
            case (4):
                faculties.sort(studentAmountDescending);
                request.getSession().setAttribute("studentSort", 3);
                break;
            case (5):
                faculties.sort(stateFundedAmountAscending);
                request.getSession().setAttribute("stateFundedSort", 6);
                break;
            case (6):
                faculties.sort(stateFundedAmountDescending);
                request.getSession().setAttribute("stateFundedSort", 5);
                break;
            default:
                break;
        }


    }


}

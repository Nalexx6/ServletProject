package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortFacultiesCommand implements Command{

    private static final Comparator<Faculty> alphabetAscending = (Comparator.comparing(Faculty::getName));

    private static final Comparator<Faculty> alphabetDescending = ((faculty1, faculty2) -> {
        return faculty2.getName().compareTo(faculty1.getName());
    });

    private static final Comparator<Faculty> studentAmount = (Comparator.comparing(Faculty::getStudentsAmount));

    private static final Comparator<Faculty> stateFundedAmount = (Comparator.comparing(Faculty::getStateFundedAmount));

    static public void setFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {

        int type = Integer.parseInt(request.getParameter("sortType"));
        FacultyService service = new FacultyService();
        List<Faculty> faculties = service.getAllFaculties();
        sort(faculties, type);
        setFaculties(request, faculties);

        return "redirect:"+request.getParameter("page-path");
    }

    private void sort(List<Faculty> faculties, int type){

        switch (type) {
            case (1):
                faculties.sort(alphabetAscending);
                break;
            case (2):
                faculties.sort(alphabetDescending);
                break;
            case (3):
                faculties.sort(studentAmount);
                break;
            case (4):
                faculties.sort(stateFundedAmount);
                break;
            default:
                break;
        }

    }


}

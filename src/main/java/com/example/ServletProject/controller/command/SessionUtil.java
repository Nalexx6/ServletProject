package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

/**
 * Util class to set session parameters after each command
 * Also updates loggedUsers parameter if login/logout command executed
 */
public class SessionUtil {

    /**
     Sets ADMIN parameters
     */
    static public void setUserParams(HttpServletRequest request, User user, List<Faculty> faculties, List<User> users,
                                    List<Submission> submissions, List<Subject> subjects) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("faculties", faculties);
        session.setAttribute("users", users);
        session.setAttribute("submissions", submissions);
        session.setAttribute("subjects", subjects);
    }

    /**
     Sets USER parameters
     */
    static public void setUserParams(HttpServletRequest request,
                                    User user, List<Faculty> faculties) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    /**
     * Sets faculties after any faculty command was executed
     */
    static public void setFaculties(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("faculties", getFacultiesWithSubmissions());
    }

    static private List<Faculty> getFacultiesWithSubmissions(){
        FacultyService service = new FacultyService();
        //set corresponding submissions for each faculty
        List<Faculty> faculties = service.getAllFaculties();
        for(Faculty f: faculties){
            List<Submission> submissions = service.findAllSubmissionsForFaculty(f);
            f.setSubmissions(submissions);
        }

        return faculties;
    }

    /**
     * Sets users with recently blocked/unblocked user
     */
    static public void setUsers(HttpServletRequest request, List<User> users){
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
    }

    /**
     * Sets submissions with their statuses for ADMIN
     */
    static public void setSubmissions(HttpServletRequest request){
        SubmissionService service = new SubmissionService();
        HttpSession session = request.getSession();
        session.setAttribute("submissions", service.getAllSubmissions());
    }

    /**
     * Sets submissions with their statuses after USER created one
     */
    static public void setSubmissions(HttpServletRequest request, User user, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    /**
     * Sets faculties after any sort was executed
     */
    static public void setSortedFaculties(HttpServletRequest request, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
    }


    /**
     * Sets finalized faculties and submissions with their statuses
     */
    static public void setFinalization(HttpServletRequest request, List<Faculty> faculties, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
        session.setAttribute("submissions", submissions);
        session.getServletContext().setAttribute("finalized", true);
    }

}

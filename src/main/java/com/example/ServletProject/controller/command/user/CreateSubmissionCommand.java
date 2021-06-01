package com.example.ServletProject.controller.command.user;

import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateSubmissionCommand implements Command {

    static public void setSubmissions(HttpServletRequest request, User user, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        SubmissionService service = new SubmissionService();
        service.addSubmission(mapSubmission(request));

        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        user.setSubmissions(userService.findAllSubmissionsForUser(user));
        setSubmissions(request, user, userService.getAllUnsubmittedFaculties(user));

        return "redirect:/login/userRes.jsp";
    }

    private Submission mapSubmission(HttpServletRequest request){
        Submission submission = new Submission();

        submission.setUser((User) request.getSession().getAttribute("user"));

        FacultyService service = new FacultyService();
        submission.setFaculty(service.getAllFaculties().get(Integer.parseInt(request.getParameter("facultyIndex"))));;

        List<Integer> grades = new ArrayList<>();
        grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE1)));
        grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE2)));
        grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE3)));
        submission.setGrades(grades);

        submission.setSecEducAvg(Double.parseDouble(request.getParameter("sec-avg")));
        submission.setChecked(false);

        return submission;
    }
}

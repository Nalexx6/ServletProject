package com.example.ServletProject.controller.command.user;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.controller.command.MessageKeys;
import com.example.ServletProject.model.entity.*;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.service.UserService;
import com.example.ServletProject.model.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateSubmissionCommand implements Command {
    private static final Logger log = LogManager.getLogger(CreateSubmissionCommand.class);

    static public void setSubmissions(HttpServletRequest request, User user, List<Faculty> faculties){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        Submission submission = mapSubmission(request);

        if(!Validator.validateSubmissionFields(submission)) {
            request.getSession().setAttribute("message", MessageKeys.SUBMISSION_INVALID);
            request.getSession().setAttribute("facIndex", submission.getFaculty().getId());
            log.trace("Invalid submission parameters");
            return "redirect:" + Paths.USER_PAGE;
        }

        SubmissionService service = new SubmissionService();
        service.addSubmission(submission);

        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        user.setSubmissions(userService.findAllSubmissionsForUser(user));
        setSubmissions(request, user, userService.getAllUnsubmittedFaculties(user));

        log.debug("Command finished");
        return "redirect:" + Paths.USER_PAGE;
    }

    private Submission mapSubmission(HttpServletRequest request){
        Submission submission = new Submission();

        submission.setUser((User) request.getSession().getAttribute("user"));

        FacultyService service = new FacultyService();
        submission.setFaculty(service.getFacultyById((Long.parseLong(request.getParameter("facultyIndex")))));

        List<Integer> grades = new ArrayList<>();
        try {
            grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE1)));
            grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE2)));
            grades.add(Integer.parseInt(request.getParameter(Fields.SUBMISSION__GRADE3)));
        } catch (NumberFormatException e){
            e.printStackTrace();
            //todo: log exception
        }
        submission.setGrades(grades);

        submission.setSecEducAvg(Double.parseDouble(request.getParameter(Fields.SUBMISSION__SEC_EDUC_AVG)));
        submission.setChecked(false);

        return submission;
    }


}

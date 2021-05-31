package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.dao.impl.JDBCSubmissionDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CreateSubmissionCommand implements Command{

    static public void setSubmissions(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Submission submission = mapSubmission(request);

        JDBCSubmissionDao sDao = new JDBCSubmissionDao();
        sDao.insert(submission);

        User user = (User) request.getSession().getAttribute("user");
        List<Submission> submissions = user.getSubmissions();

        if(submissions == null){
            submissions = new ArrayList<>();
        }
        submissions.add(submission);
        user.setSubmissions(submissions);
        setSubmissions(request, user);

        return "redirect:/login/userRes.jsp";
    }

    private Submission mapSubmission(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        JDBCFacultyDao fDao = new JDBCFacultyDao();
        List<Faculty> faculties = fDao.findAll();
        Faculty faculty = faculties.get(Integer.parseInt(request.getParameter("facultyIndex")));

        Submission submission = new Submission();
        submission.setUser(user);
        submission.setFaculty(faculty);

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

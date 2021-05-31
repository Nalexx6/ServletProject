package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCSubmissionDao;
import com.example.ServletProject.model.entity.Submission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckSubmissionCommand implements Command {

    static private void setSubmissions(HttpServletRequest request, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("submissions", submissions);
    }

    @Override
    public String execute(HttpServletRequest request) {

        JDBCSubmissionDao sDao = new JDBCSubmissionDao();
        Submission submission = sDao.findAll().get(Integer.parseInt(request.getParameter("opIndex")));
        submission.setChecked(true);
        sDao.update(submission);

        setSubmissions(request, sDao.findAll());
        return "redirect:/login/adminRes.jsp";
    }
}

package com.example.ServletProject.controller.command.admin;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.Command;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubmissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class FinalizeCertificateCommand implements Command {
    private static final Logger log = LogManager.getLogger(FinalizeCertificateCommand.class);

    /**
     Comparator for sorting submissions during finalization
     */
    private static final Comparator<Submission> gradesComparator = ((submission1, submission2) -> {
            if((submission2.getGrades().get(0) + submission2.getGrades().get(1) + submission2.getGrades().get(2)) >
            (submission1.getGrades().get(0) + submission1.getGrades().get(1) + submission1.getGrades().get(2))) {
                return 1;
            } else if ((submission2.getGrades().get(0) + submission2.getGrades().get(1) + submission2.getGrades().get(2)) <
                    (submission1.getGrades().get(0) + submission1.getGrades().get(1) + submission1.getGrades().get(2))){
                return -1;
            } else {
                return submission2.getSecEducAvg().compareTo(submission1.getSecEducAvg());
            }

    });

    /**
     * Sets finalized faculties and submissions with their statuses
     */
    private void setFinalization(HttpServletRequest request, List<Faculty> faculties, List<Submission> submissions){
        HttpSession session = request.getSession();
        session.setAttribute("faculties", faculties);
        session.setAttribute("submissions", submissions);
        session.getServletContext().setAttribute("finalized", true);
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command starts");

        FacultyService service = new FacultyService();
        List<Faculty> faculties = service.getAllFaculties();

        for(Faculty f: faculties){
            f.setSubmissions(service.findAllSubmissionsForFaculty(f));
            finalise(f);
        }

        SubmissionService submissionService = new SubmissionService();
        setFinalization(request, faculties, submissionService.getAllSubmissions());

        log.debug("Command finished");
        return "redirect:" + Paths.ADMIN_PAGE;
    }

    /**
     * Sorts faculty submissions by their grades, and updates their statuses
     */
    private void finalise(Faculty faculty){
        faculty.getSubmissions().sort(gradesComparator);

        SubmissionService service = new SubmissionService();

        for(int i = 0; i < faculty.getSubmissions().size(); i++){
            if(i < faculty.getStateFundedAmount()){
                service.finalizeSubmission(faculty.getSubmissions().get(i), 2); //state funded
            } else if (i < faculty.getStudentsAmount()) {
                service.finalizeSubmission(faculty.getSubmissions().get(i), 1); //fee payed
            } else {
                service.finalizeSubmission(faculty.getSubmissions().get(i), 0); //failed to enter
            }
        }
    }
}

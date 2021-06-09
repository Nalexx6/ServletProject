package modelTest;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Subject;
import com.example.ServletProject.model.entity.Submission;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.SubjectService;
import com.example.ServletProject.model.service.SubmissionService;
import com.example.ServletProject.model.service.UserService;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    /**
     * This test checks, if there are bijection between all submissions
     * And set of submissions among all faculties
     */
    @Test
    public void submissionFacultyServiceTest(){
        SubmissionService submissionService = new SubmissionService();
        List<Submission> submissions = submissionService.getAllSubmissions();

        FacultyService facultyService = new FacultyService();
        List<Faculty> faculties = facultyService.getAllFaculties();
        int size = 0;
        for(Faculty f: faculties){
            f.setSubmissions(facultyService.findAllSubmissionsForFaculty(f));
            size += f.getSubmissions().size();
            for(Submission s: f.getSubmissions()){
                assertTrue(submissions.contains(s));
            }
        }

        assertEquals(size, submissions.size());
    }

    /**
     * This test checks, if there are bijection between all submissions
     * And set of submissions among all users
     */
    @Test
    public void submissionUserServiceTest(){
        SubmissionService submissionService = new SubmissionService();
        List<Submission> submissions = submissionService.getAllSubmissions();

        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        int size = 0;
        for(User u: users){
            u.setSubmissions(userService.findAllSubmissionsForUser(u));
            size += u.getSubmissions().size();
            for(Submission s: u.getSubmissions()){
                assertTrue(submissions.contains(s));
            }
        }

        assertEquals(size, submissions.size());
    }

    @Test
    public void facultyServiceTest(){
        FacultyService service = new FacultyService();
        List<Faculty> faculties = service.getAllFaculties();

        for(Faculty f: faculties){
            assertEquals(service.getFacultyById(f.getId()), service.getFacultyByName(f.getName()));
        }
    }

    @Test
    public void userServiceTest(){
        UserService service = new UserService();
        List<User> users = service.getAllUsers();

        for(User u: users){
            assertEquals(service.getUserById(u.getId()), service.getUserByLogin(u.getLogin()));
        }
    }
    
    @Test
    public void submissionServiceTest(){
        SubmissionService service = new SubmissionService();
        List<Submission> submissions = service.getAllSubmissions();

        for(Submission s: submissions){
            assertEquals(s, service.getSubmissionById(s.getId()));
        }
    }

    @Test
    public void subjectServiceTest(){
        SubjectService service = new SubjectService();
        List<Subject> Subjects = service.getAllSubjects();

        for(Subject s: Subjects){
            assertEquals(s, service.getSubjectByName(s.getName()));
        }
    }
       
    
}

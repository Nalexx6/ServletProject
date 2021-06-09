package controllerTest.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.admin.CreateFacultyCommand;
import com.example.ServletProject.controller.command.user.CreateSubmissionCommand;
import com.example.ServletProject.model.entity.Fields;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSubmissionCommandTest {

    @Test
    public void createInvalidTest() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

        Mockito.when(req.getParameter("facultyIndex"))
                .thenReturn("1");
        Mockito.when(req.getParameter(Fields.SUBMISSION__GRADE1))
                .thenReturn("r");
        Mockito.when(req.getParameter(Fields.SUBMISSION__GRADE2))
                .thenReturn("10");
        Mockito.when(req.getParameter(Fields.SUBMISSION__GRADE3))
                .thenReturn("10");
        Mockito.when(req.getParameter(Fields.SUBMISSION__SEC_EDUC_AVG))
                .thenReturn("10.5");

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        CreateSubmissionCommand command = new CreateSubmissionCommand();

        assertEquals("redirect:" + Paths.USER_PAGE, command.execute(req));
        Mockito.verify(req, Mockito.times(3)).getSession();
    }
}

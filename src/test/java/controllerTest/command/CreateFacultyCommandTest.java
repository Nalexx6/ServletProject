package controllerTest.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.admin.CreateFacultyCommand;
import com.example.ServletProject.model.entity.Fields;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

public class CreateFacultyCommandTest {

    @Test
    public void createInvalidTest() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getParameter(Fields.FACULTY__NAME))
                .thenReturn("Test");
        Mockito.when(req.getParameter(Fields.FACULTY__STUDENT_AMOUNT))
                .thenReturn("20");
        Mockito.when(req.getParameter(Fields.FACULTY__STATE_FUNDED_AMOUNT))
                .thenReturn("10");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB1_ID))
                .thenReturn("Math");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB2_ID))
                .thenReturn("English");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB3_ID))
                .thenReturn("1");

        Mockito.when(req.getSession()).thenReturn(session);

        CreateFacultyCommand command = new CreateFacultyCommand();

        assertEquals("redirect:" + Paths.ADMIN_PAGE, command.execute(req));
        Mockito.verify(req, Mockito.times(2)).getSession();

    }

    @Test
    public void createExistsTest() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getParameter(Fields.FACULTY__NAME))
                .thenReturn("Cybernetics");
        Mockito.when(req.getParameter(Fields.FACULTY__STUDENT_AMOUNT))
                .thenReturn("20");
        Mockito.when(req.getParameter(Fields.FACULTY__STATE_FUNDED_AMOUNT))
                .thenReturn("10");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB1_ID))
                .thenReturn("Math");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB2_ID))
                .thenReturn("English");
        Mockito.when(req.getParameter(Fields.FACULTY__SUB3_ID))
                .thenReturn("Physics");

        Mockito.when(req.getSession()).thenReturn(session);

        CreateFacultyCommand command = new CreateFacultyCommand();

        assertEquals("redirect:" + Paths.ADMIN_PAGE, command.execute(req));
        Mockito.verify(req, Mockito.times(2)).getSession();

    }

}

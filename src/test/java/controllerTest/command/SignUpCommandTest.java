package controllerTest.command;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.command.SignUpCommand;
import com.example.ServletProject.controller.command.admin.CreateFacultyCommand;
import com.example.ServletProject.model.entity.Fields;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpCommandTest {

    @Test
    public void invalidParamTest() {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

        Mockito.when(req.getParameter(Fields.USER__LOGIN))
                .thenReturn("@dfd");
        Mockito.when(req.getParameter(Fields.USER__PASSWORD))
                .thenReturn("1111");
        Mockito.when(req.getParameter(Fields.USER__FIRST_NAME))
                .thenReturn("Test");
        Mockito.when(req.getParameter(Fields.USER__LAST_NAME))
                .thenReturn("Test");
        Mockito.when(req.getParameter(Fields.USER__EMAIL))
                .thenReturn("test@test.com");
        Mockito.when(req.getParameter(Fields.USER__CITY))
                .thenReturn("Kyiv");
        Mockito.when(req.getParameter(Fields.USER__REGION))
                .thenReturn("Kyiv");
        Mockito.when(req.getParameter(Fields.USER__INSTITUTION))
                .thenReturn("Kyiv");

        SignUpCommand command = new SignUpCommand();
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);

        assertEquals("redirect:" + Paths.SIGN_UP_PAGE, command.execute(req));
        Mockito.verify(req, Mockito.times(1)).getSession();
    }
}

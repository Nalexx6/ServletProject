package controllerTest;

import com.example.ServletProject.controller.Paths;
import com.example.ServletProject.controller.Servlet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletTest {

    @Test
    public void doGetTest() throws IOException, ServletException {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getParameter("command"))
                .thenReturn("testCommand");
        Mockito.when(req.getRequestDispatcher(Paths.MAIN_PAGE)).thenReturn(dispatcher);
        Mockito.when(req.getSession()).thenReturn(session);

        Servlet servlet = new Servlet();
        servlet.doGet(req, resp);

        Mockito.verify(dispatcher).forward(req, resp);
    }

    @Test
    public void doPostTest() throws IOException, ServletException {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(req.getParameter("command"))
                .thenReturn("testCommand");
        Mockito.when(req.getRequestDispatcher(Paths.MAIN_PAGE)).thenReturn(dispatcher);
        Mockito.when(req.getSession()).thenReturn(session);

        Servlet servlet = new Servlet();
        servlet.doPost(req, resp);

        Mockito.verify(dispatcher).forward(req, resp);
    }
}

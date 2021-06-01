package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.validator.Regex;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.UserService;
import com.example.ServletProject.model.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpCommand implements Command{

    static private void setUserRole(HttpServletRequest request,
                                    User user, List<Faculty> faculties) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        System.out.println("Logging in user");
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        session.setAttribute("role", user.getRole());
        session.setAttribute("user", user);
        session.setAttribute("faculties", faculties);
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = mapUser(request);
//        String pass = request.getParameter();
        UserService userService = new UserService();
        if(!Validator.validateUserFields(user) || userService.findUserByLogin(user.getLogin()) != null){
            request.getSession().setAttribute("message", "Please enter valid user parameters");
            return "redirect:/login/userSignUp.jsp";
        }

        userService.addUser(user);
        FacultyService facultyService = new FacultyService();
        setUserRole(request, user, facultyService.getAllFaculties());
        if(user.getRole().equals("ADMIN")) {
            return /*redirect:*/"/login/adminRes.jsp";
        } else {
            return /*redirect:*/"/login/userRes.jsp";
        }
    }

    public static User mapUser(HttpServletRequest request){
        User user = new User();

        user.setLogin(request.getParameter(Fields.USER__LOGIN));
        user.setPassword(request.getParameter(Fields.USER__PASSWORD));
        user.setFirstName(request.getParameter(Fields.USER__FIRST_NAME));
        user.setLastName(request.getParameter(Fields.USER__LAST_NAME));
        user.setEmail(request.getParameter(Fields.USER__EMAIL));
        user.setRole("USER");
        user.setCity(request.getParameter(Fields.USER__CITY));
        user.setRegion(request.getParameter(Fields.USER__REGION));
        user.setInstitution(request.getParameter(Fields.USER__INSTITUTION));

        return user;
    }

}

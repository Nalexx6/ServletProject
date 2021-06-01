package com.example.ServletProject.controller.command;

import com.example.ServletProject.model.dao.impl.JDBCFacultyDao;
import com.example.ServletProject.model.dao.impl.JDBCUserDao;
import com.example.ServletProject.model.entity.Faculty;
import com.example.ServletProject.model.entity.Fields;
import com.example.ServletProject.model.entity.User;
import com.example.ServletProject.model.service.FacultyService;
import com.example.ServletProject.model.service.UserService;

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
        if(!validateUserData(user) || userService.findUserByLogin(user.getLogin()) != null){
            System.out.println("kfdkfld");
            return null;
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

    private boolean validateUserData(User user){
        return Pattern.compile("^[A-Z][a-z]+$").matcher(user.getFirstName()).find() &&
                Pattern.compile("^[A-Z][a-z]+$").matcher(user.getLastName()).find() &&
                Pattern.compile("^[A-Z][a-z]+$").matcher(user.getCity()).find();
        //todo: improve validation
    }


}

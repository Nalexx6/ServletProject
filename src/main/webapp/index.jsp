<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h2>
        Admission committee <br/>
    </h2>

    <br/>
    <a href="${pageContext.request.contextPath}/login/userLogin.jsp" style="font-size: 2.0em">Log in</a>
    <a href="${pageContext.request.contextPath}/login/userSignUp.jsp" style="font-size: 2.0em">Sign Up</a>
    <br>
</body>
</html>
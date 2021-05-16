<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.*, java.text.*" %>

<%!
    String getFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h2>
        Admission committee <br/>
        <i>Сегодня <%= getFormattedDate() %></i>
    </h2>

    <br/>
    <a href="${pageContext.request.contextPath}/login/userLogin.jsp" style="font-size: 2.0em">Log in</a>
    <br>
    <a href="${pageContext.request.contextPath}/login/adminLogin.jsp" style="font-size: 2.0em">Log in as admin</a>
    <br>
    <a href="${pageContext.request.contextPath}/exception">Exception</a>
    <br>
</body>
</html>
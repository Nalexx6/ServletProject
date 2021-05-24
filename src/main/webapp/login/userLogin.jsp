<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/15/2021
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="login"/>


        <div class="form-control">
            <h1 class="header">Log into system</h1>
            <label>User</label>
            <input type="text" name="login" placeholder="Enter Login"><br/>

            <label>Password</label>
            <input type="text" name="password" placeholder="Enter Password"><br/>

            <input class="button btn" type="submit" value="Log In">
            <a class="link" href="${pageContext.request.contextPath}/index.jsp">Back to main page</a>
        </div>
    </form>

</body>
</html>

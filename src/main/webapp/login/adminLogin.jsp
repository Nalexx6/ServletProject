<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<form class="container " method="post" action="${pageContext.request.contextPath}/login">

    <div class="form-control">
        <h1 class="header">Log into system</h1>
        <label>First Name</label>
        <input type="text" name="firstName" placeholder="Enter First Name"><br/>

        <label>Last Name</label>
        <input type="text" name="lastName" placeholder="Enter Last Name"><br/>

        <label>Email</label>
        <input type="email" name="email" placeholder="Enter Email"><br/>

        <input class="button btn" type="submit" value="Log In">
        <a class="link" href="${pageContext.request.contextPath}/index.jsp">Back to main page</a>
    </div>
</form></body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/21/2021
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
    <input type="hidden" name="command" value="signUp"/>


    <div class="form-control">
        <h1 class="header">Sign up to system</h1>
        <label>Login</label>
        <input type="text" name="login" placeholder="Enter Login"><br/>

        <label>Password</label>
        <input type="text" name="password" placeholder="Enter Password"><br/>

        <label>First Name</label>
        <input type="text" name="first_name" placeholder="Enter First Name"><br/>

        <label>Last Name</label>
        <input type="text" name="last_name" placeholder="Enter Last Name"><br/>

        <label>Email</label>
        <input type="email" name="email" placeholder="Enter Email"><br/>

        <label>City</label>
        <input type="text" name="city" placeholder="Enter City"><br/>

        <label>Region</label>
        <input type="text" name="region" placeholder="Enter Region"><br/>

        <label>Institution</label>
        <input type="text" name="institution" placeholder="Enter Institution"><br/>

        <input class="button btn" type="submit" value="Sign Up">
        <a class="link" href="${pageContext.request.contextPath}/index.jsp">Back to main page</a>
    </div>
</form>

</body>
</html>


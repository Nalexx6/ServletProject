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
<form class="container ">

    <div class="form-control">
        <h1 class="header">Hello Admin</h1>
        <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
        <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
            <p>${faculties.get(f).name}</p>
        </c:forEach>
    </div>
</form></body>
</html>

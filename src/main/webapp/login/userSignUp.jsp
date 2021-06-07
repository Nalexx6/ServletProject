<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/21/2021
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" var="bundle" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="signUp.label"/></title>
    <link rel="stylesheet" href="index.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400&display=swap');

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;

        }

        table tr th {
            border-bottom: 2px solid black;
            padding-left: 5px;
            padding-right: 5px;
        }

        .container {
            min-width: 500px;
            margin: 30px auto;
            overflow: auto;
            min-height: 300px;
            border: 1px solid steelblue;
            padding: 30px;
            border-radius: 5px;
            font-size: 17px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            text-align: center;
        }

        .btn {
            display: inline-block;
            background: #000;
            max-width: 220px;
            color: #fff;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 15px;
            font-family: inherit;
        }

        .btn:focus {
            outline: none;
        }

        .btn:active {
            transform: scale(0.98);
        }

        .form-control {
            margin: 20px 0;
        }

        .form-control label {
            display: block;
        }

        .form-control input {
            width: 100%;
            height: 40px;
            margin: 5px;
            padding: 3px 7px;
            font-size: 17px;
        }
    </style>
</head>
<body>

    <div id="locale-changer" class="form-control" style="margin: 0">
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="changeLocale">
            <input type="hidden" name="page-path" value="/login/userSignUp.jsp">
            <input class="btn" style="background: lightgray" type="submit" name="locale" value="UA">
        </form>
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="changeLocale">
            <input type="hidden" name="page-path" value="/login/userSignUp.jsp">
            <input class="btn" style="background: lightgray" type="submit" name="locale" value="EN">
        </form>
    </div>

    <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="signUp"/>


        <div class="form-control">
            <h1 class="header"><fmt:message key="header.signUp"/></h1>
            <h2 style="color: red; text-align: center">${sessionScope.message}</h2>
            <label for="login-input"><fmt:message key="user.label.login"/></label>
            <input id="login-input" type="text" name="login"
                   placeholder="<fmt:message key="signUp.placeholder.login"/>"><br/>

            <label for="pass-input"><fmt:message key="user.label.password"/></label>
            <input id="pass-input" type="text" name="password"
                   placeholder="<fmt:message key="signUp.placeholder.password"/>"><br/>

            <label for="fName"><fmt:message key="user.label.firstName"/></label>
            <input id="fName" type="text" name="first_name"
                   placeholder="<fmt:message key="signUp.placeholder.firstName"/>"><br/>

            <label for="lName"><fmt:message key="user.label.lastName"/></label>
            <input id="lName" type="text" name="last_name"
                   placeholder="<fmt:message key="signUp.placeholder.lastName"/>"><br/>

            <label for="email-input"><fmt:message key="user.label.email"/></label>
            <input id="email-input" type="email" name="email"
                   placeholder="<fmt:message key="signUp.placeholder.email"/>"><br/>

            <label for="city-input"><fmt:message key="user.label.city"/></label>
            <input id="city-input" type="text" name="city"
                   placeholder="<fmt:message key="signUp.placeholder.city"/>"><br/>

            <label for="region-input"><fmt:message key="user.label.region"/></label>
            <input id="region-input" type="text" name="region"
                   placeholder="<fmt:message key="signUp.placeholder.region"/>"><br/>

            <label for="inst"><fmt:message key="user.label.institution"/></label>
            <input id="inst" type="text" name="institution"
                   placeholder="<fmt:message key="signUp.placeholder.institution"/>"><br/>

            <input class="button btn" type="submit" value="<fmt:message key="button.signUp"/>">
            <a class="link" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="entry.backToMain"/></a>
        </div>
    </form>

</body>
</html>


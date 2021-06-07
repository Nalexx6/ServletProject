<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/15/2021
  Time: 6:04 PM
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
    <title><fmt:message key="login.label"/></title>
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
            <input type="hidden" name="page-path" value="/login/userLogin.jsp">
            <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA">
        </form>
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="changeLocale">
            <input type="hidden" name="page-path" value="/login/userLogin.jsp">
            <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale" value="EN">
        </form>
    </div>

    <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="login"/>
        <div class="form-control">
            <h1 class="header"><fmt:message key="header.login"/></h1>
            <c:if test="${sessionScope.message != null}">
                <h2 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h2>
            </c:if>
            <label for="login-input"><fmt:message key="user.label.login"/></label>
            <input id="login-input" type="text" name="login"
                   placeholder="<fmt:message key="login.login.placeholder"/>"><br/>

            <label for="pass-input"><fmt:message key="user.label.password"/></label>
            <input id="pass-input" type="text" name="password"
                   placeholder="<fmt:message key="login.password.placeholder"/>"><br/>

            <input class="button btn" type="submit" value="<fmt:message key="button.login"/>">
            <a class="link" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="entry.backToMain"/></a>
        </div>
    </form>

</body>
</html>

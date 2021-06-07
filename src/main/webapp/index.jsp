<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" var="bundle" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title>
        <fmt:message key="entry.name"/>
    </title>
    <link rel="stylesheet" href="login/index.css">
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

    <div class="container">
        <div id="locale-changer" class="form-control" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/index.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="EN">
            </form>
        </div>
        <c:out value="${requestScope.message}"/>
        <h2 class="header">
            <fmt:message key="entry.name"/> <br/>
        </h2>

        <br/>
        <a href="${pageContext.request.contextPath}/login/userLogin.jsp" style="font-size: 2.0em">
            <fmt:message key="login.label"/>
        </a>
        <c:if test="${!applicationScope.finalized}">
        <br>
        <a href="${pageContext.request.contextPath}/login/userSignUp.jsp" style="font-size: 2.0em">
            <fmt:message key="signUp.label"/>
        </a>
        <br>
        </c:if>
    </div>
</body>
</html>
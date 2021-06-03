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

</head>
<body>


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
    <h2>
        <fmt:message key="entry.name"/> <br/>
    </h2>

    <br/>
    <a href="${pageContext.request.contextPath}/login/userLogin.jsp" style="font-size: 2.0em">
        <fmt:message key="login.label"/>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/login/userSignUp.jsp" style="font-size: 2.0em">
        <fmt:message key="signUp.label"/>
    </a>
    <br>
</body>
</html>
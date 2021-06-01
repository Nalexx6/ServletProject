<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${sessionScope.locale}" />--%>
<fmt:setBundle basename="resources_ru" />

<!DOCTYPE html>
<html>
<head>
    <title>
        <fmt:message key="entry.name"/>
    </title>
</head>
<body>
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
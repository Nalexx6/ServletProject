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
            <label><fmt:message key="user.label.login"/></label>
            <input type="text" name="login" placeholder="<fmt:message key="signUp.placeholder.login"/>"><br/>

            <label><fmt:message key="user.label.password"/></label>
            <input type="text" name="password" placeholder="<fmt:message key="signUp.placeholder.password"/>"><br/>

            <label><fmt:message key="user.label.firstName"/></label>
            <input type="text" name="first_name" placeholder="<fmt:message key="signUp.placeholder.firstName"/>"><br/>

            <label><fmt:message key="user.label.lastName"/></label>
            <input type="text" name="last_name" placeholder="<fmt:message key="signUp.placeholder.lastName"/>"><br/>

            <label><fmt:message key="user.label.email"/></label>
            <input type="email" name="email" placeholder="<fmt:message key="signUp.placeholder.email"/>"><br/>

            <label><fmt:message key="user.label.city"/></label>
            <input type="text" name="city" placeholder="<fmt:message key="signUp.placeholder.city"/>"><br/>

            <label><fmt:message key="user.label.region"/></label>
            <input type="text" name="region" placeholder="<fmt:message key="signUp.placeholder.region"/>"><br/>

            <label><fmt:message key="user.label.institution"/></label>
            <input type="text" name="institution" placeholder="<fmt:message key="signUp.placeholder.institution"/>"><br/>

            <input class="button btn" type="submit" value="<fmt:message key="button.signUp"/>">
            <a class="link" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="entry.backToMain"/></a>
        </div>
    </form>

</body>
</html>


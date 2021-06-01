<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/15/2021
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<fmt:setLocale value="${sessionScope.locale}" />--%>
<fmt:setBundle basename="resources_en" />
<html>
<head>
    <title><fmt:message key="login.label"/></title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="login"/>
        <div class="form-control">
            <h1 class="header"><fmt:message key="header.login"/></h1>
            <h2 style="color: red; text-align: center">${sessionScope.message}</h2>
            <label><fmt:message key="user.label.login"/></label>
            <input type="text" name="login" placeholder="<fmt:message key="login.login.placeholder"/>"><br/>

            <label><fmt:message key="user.label.password"/></label>
            <input type="text" name="password" placeholder="<fmt:message key="login.password.placeholder"/>"><br/>

            <input class="button btn" type="submit" value="<fmt:message key="button.login"/>">
            <a class="link" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="entry.backToMain"/></a>
        </div>
    </form>

</body>
</html>

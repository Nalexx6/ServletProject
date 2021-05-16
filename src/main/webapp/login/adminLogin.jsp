<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Log into system</h1><br/>
<form method="post" action="${pageContext.request.contextPath}/login">

    <input type="text" name="firstName"><br/>
    <input type="text" name="lastName"><br/>
    <input type="email" name="email"><br/>

    <input class="button" type="submit" value="Войти">

</form>
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>
</body>
</html>

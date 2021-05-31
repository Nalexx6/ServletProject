<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/26/2021
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
    
    <h1>Error</h1>
    <c:out value="${requestScope.message}"/>
    <a class="link" href="${pageContext.request.contextPath}/index.jsp">Back to main page</a>

</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" var="bundle" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="entry.name"/> </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/index.css">
</head>
<body>
    <div class="container">

        <div id="locale-changer" class="form-control" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login/userRes.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login/userRes.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="EN">
            </form>
        </div>

        <div class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="logout">
                <input class="btn" style="background: red" type="submit" value="<fmt:message key="button.logout"/>">
            </form>
            <input class="btn" type="button" value="<fmt:message key="button.profile"/>" onclick="showUserCredentials()">
            <input class="btn" type="button" value="<fmt:message key="button.submission"/>" onclick="showSubmissions()">
            <input class="btn" type="button" value="<fmt:message key="button.submission.create"/>" onclick="createSubmission()">
            <h1 id="header" class="header"><fmt:message key="header.submission"/></h1>
        </div>

        <div id="pers-inf" style="display: none">
            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <p><fmt:message key="user.label.firstName"/>: <c:out value="${user.firstName}"/>
            <p><fmt:message key="user.label.lastName"/>: <c:out value="${user.lastName}"/>
            <p><fmt:message key="user.label.email"/>: <c:out value="${user.email}"/>
            <p><fmt:message key="user.label.city"/>: <c:out value="${user.city}"/>
            <p><fmt:message key="user.label.region"/>: <c:out value="${user.region}"/>
            <p><fmt:message key="user.label.institution"/>: <c:out value="${user.institution}"/>
        </div>

        <div id="submissions" style="display: block">
            <c:set var = "submissions" scope="session" value="${sessionScope.user.submissions}"/>
            <c:forEach var="s" items="${submissions}">
                <span>${s.faculty.name}</span>
                <c:set var="color" value="${s.checked ? 'blue' : 'red'}"/>
                <input class="btn" type="button"
                       style="background: ${color}" value="${s.checked ? "Checked" : "Unchecked"}"/>
                <br>
            </c:forEach>
        </div>

        <div id="faculties" style="display: none">
            <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
            <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
                <span id="fac-${f}-name">${faculties.get(f).name}</span>
                <input type="hidden" id="fac-${f}-st-amount" value="${faculties.get(f).studentsAmount}"/>
                <input type="hidden" id="fac-${f}-st-funded-amount" value="${faculties.get(f).stateFundedAmount}"/>
                <input type="hidden" id="fac-${f}-subject1" value="${faculties.get(f).subjects.get(0).name}"/>
                <input type="hidden" id="fac-${f}-subject2" value="${faculties.get(f).subjects.get(1).name}"/>
                <input type="hidden" id="fac-${f}-subject3" value="${faculties.get(f).subjects.get(2).name}"/>
                <input class="btn" type="button" value="<fmt:message key="submission.create.order"/>"
                       onclick="orderSubmission(${f})"/>
                <br>
            </c:forEach>

        </div>

        <div class="form-control" id="create-submission" style="display: none" >
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="createSubmission">
                <input type="hidden" id="sub-fac-index" name="facultyIndex" value="0">
                <h2 style="color: red; text-align: center">${sessionScope.message}</h2>
                <label id="subject1_lbl" for="subject1"></label>
                <input type="text" id="subject1" name="grade1"
                       placeholder="<fmt:message key="submission.placeholder.subject1"/>">

                <label id="subject2_lbl" for="subject2"></label>
                <input type="text" id="subject2" name="grade2"
                       placeholder="<fmt:message key="submission.placeholder.subject2"/>">

                <label id="subject3_lbl" for="subject3"></label>
                <input type="text" id="subject3" name="grade3"
                       placeholder="<fmt:message key="submission.placeholder.subject3"/>">

                <label for="sec-avg"><fmt:message key="submission.label.sec_avg"/></label>
                <input type="text" id="sec-avg" name="sec-avg"
                       placeholder="<fmt:message key="submission.placeholder.sec_avg"/>"><br/>

                <input class="btn" type="submit" value="<fmt:message key="button.submit"/>">
            </form>
        </div>
    </div>



</body>
</html>

<script>

    function showUserCredentials(){
        document.getElementById('pers-inf').style.display = 'block';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.profile"/>';
    }

    function showSubmissions(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.submission"/>';
    }

    function createSubmission(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.submission.create"/>';
    }

    function orderSubmission(index){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-submission').style.display = 'block';
        document.getElementById('header').innerHTML = '<fmt:message key="header.submission.chosen"/>' + ' ' +
                        document.getElementById('fac-' + index + '-name').innerText;

        document.getElementById('sub-fac-index').value = index;

        document.getElementById("subject1_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject1").value;
        document.getElementById("subject2_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject2").value;
        document.getElementById("subject3_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject3").value;

        document.getElementById("subject1").value = "";
        document.getElementById("subject2").value = "";
        document.getElementById("subject3").value = "";
    }
</script>

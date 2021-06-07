
<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" var="bundle" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="entry.name"/> </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/index.css">
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

        <input type="hidden" id="finalized" value="${applicationScope.finalized}">

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
            <c:if test="${!applicationScope.finalized}">
                <input class="btn" id="create-btn" type="button" value="<fmt:message key="button.submission.create"/>"
                       onclick="createSubmission()">
            </c:if>
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

        <div id="submissions" style="display: none">
            <c:set var = "submissions" scope="session" value="${sessionScope.user.submissions}"/>
            <table>
                <tr>
                    <th><fmt:message key="faculty.label.name"/></th>
                    <th><fmt:message key="faculty.label.subject1"/></th>
                    <th><fmt:message key="submission.label.grade"/></th>
                    <th><fmt:message key="faculty.label.subject2"/></th>
                    <th><fmt:message key="submission.label.grade"/></th>
                    <th><fmt:message key="faculty.label.subject3"/></th>
                    <th><fmt:message key="submission.label.grade"/></th>
                </tr>
                <c:forEach var="s" items="${submissions}">
                        <th><span>${s.faculty.name}</span></th>
                        <th><span>${s.faculty.subjects.get(0).name}</span></th>
                        <th><span>${s.grades.get(0)}</span></th>
                        <th><span>${s.faculty.subjects.get(1).name}</span></th>
                        <th><span>${s.grades.get(1)}</span></th>
                        <th><span>${s.faculty.subjects.get(2).name}</span></th>
                        <th><span>${s.grades.get(2)}</span></th>
                        <c:choose>
                            <c:when test="${applicationScope.finalized}">
                                <c:choose>
                                    <c:when test="${s.finalizationStatus == 0}">
                                        <c:set var="color" value="red"/>
                                        <fmt:message key="submission.failed" var="status"/>
                                    </c:when>
                                    <c:when test="${s.finalizationStatus == 1}">
                                        <c:set var="color" value="blue"/>
                                        <fmt:message key="submission.feePayed" var="status"/>
                                    </c:when>
                                    <c:when test="${s.finalizationStatus == 2}">
                                        <c:set var="color" value="green"/>
                                        <fmt:message key="submission.stateFunded" var="status"/>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${!applicationScope.finalized}">
                                <c:choose>
                                    <c:when test="${s.checked}">
                                        <c:set var="color" value="blue"/>
                                        <fmt:message key="submission.checked" var="status"/>
                                    </c:when>
                                    <c:when test="${!s.checked}">
                                        <c:set var="color" value="red"/>
                                        <fmt:message key="submission.unchecked" var="status"/>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                        <th style="border-bottom: 0"><input class="btn" type="button"
                                style="background: ${color}" value="${status}"/></th>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="faculties" style="display: block">
            <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
            <table>
                <input id="show-sort" type="hidden" value="${sessionScope.sortDisplayed}">
                <tr>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.alphabetSort}">
                            <input type="hidden" name="page-path" value="/login/userRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.name"/>"/>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.studentSort}">
                            <input type="hidden" name="page-path" value="/login/userRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.students_amount"/>"/>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.stateFundedSort}">
                            <input type="hidden" name="page-path" value="/login/userRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.state_funded_amount"/>"/>
                        </form>
                    </th>
                    <th><fmt:message key="faculty.label.subject1"/></th>
                    <th><fmt:message key="faculty.label.subject2"/></th>
                    <th><fmt:message key="faculty.label.subject3"/></th>
                </tr>
                <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
                    <tr>
                        <th><span id="fac-${faculties.get(f).id}-name">${faculties.get(f).name}</span></th>
                        <th><span id="fac-${faculties.get(f).id}-st-amount">${faculties.get(f).studentsAmount}</span></th>
                        <th><span id="fac-${faculties.get(f).id}-st-funded-amount">${faculties.get(f).stateFundedAmount}</span></th>
                        <th><span id="fac-${faculties.get(f).id}-subject1">${faculties.get(f).subjects.get(0).name}</span></th>
                        <th><span id="fac-${faculties.get(f).id}-subject2">${faculties.get(f).subjects.get(1).name}</span></th>
                        <th><span id="fac-${faculties.get(f).id}-subject3">${faculties.get(f).subjects.get(2).name}</span></th>
                        <th style="border-bottom: 0;"><input class="btn" type="button"
                             value="<fmt:message key="submission.create.order"/>"onclick="orderSubmission(${faculties.get(f).id})"/></th>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <div class="form-control" id="create-submission" style="display: none" >
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="createSubmission">
                <input type="hidden" id="sub-fac-index" name="facultyIndex" value="0">
                <h2 id="error-message" style="color: red; text-align: center">${sessionScope.message}</h2>
                <input type="hidden" id="fac-error-index" value="${sessionScope.facIndex}">
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

    window.onload = init;

    function init(){
        if(document.getElementById("error-message").innerText !== ""){
            orderSubmission(document.getElementById("fac-error-index").value);
        } else if(document.getElementById("show-sort").value === "1"){
            createSubmission();
        } else {
            showSubmissions();
        }
    }

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

        document.getElementById("subject1_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject1").innerText;
        document.getElementById("subject2_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject2").innerText;
        document.getElementById("subject3_lbl").innerHTML = document.getElementById("fac-"+ index +"-subject3").innerText;

        document.getElementById("subject1").value = "";
        document.getElementById("subject2").value = "";
        document.getElementById("subject3").value = "";
    }
</script>

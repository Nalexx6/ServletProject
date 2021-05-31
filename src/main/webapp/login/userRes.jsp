
<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admission committee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/index.css">
</head>
<body>
    <div class="container">

        <div class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="logout">
                <input class="btn" style="background: red" type="submit" value="Logout">
            </form>
            <input class="btn" type="button" value="Profile" onclick="showUserCredentials()">
            <input class="btn" type="button" value="Submissions" onclick="showSubmissions()">
            <input class="btn" type="button" value="Create new submission" onclick="createSubmission()">
            <h1 id="header" class="header">Your Submissions</h1>
        </div>

        <div id="pers-inf" style="display: none">
            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <p>First Name: <c:out value="${user.firstName}"/>
            <p>Last Name: <c:out value="${user.lastName}"/>
            <p>Email: <c:out value="${user.email}"/>
            <p>City: <c:out value="${user.city}"/>
            <p>Region: <c:out value="${user.region}"/>
            <p>School: <c:out value="${user.institution}"/>
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
                <input class="btn" type="button" value="Order"
                       onclick="orderSubmission(${f})"/>
                <br>
            </c:forEach>

        </div>

        <div class="form-control" id="create-submission" style="display: none" >
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="createSubmission">
                <input type="hidden" id="sub-fac-index" name="facultyIndex" value="0">

                <label id="subject1_lbl" for="subject1">First Subject</label>
                <input type="text" id="subject1" name="grade1" placeholder="Enter grade for first subject">

                <label id="subject2_lbl" for="subject2">Second Subject</label>
                <input type="text" id="subject2" name="grade2" placeholder="Enter grade for second subject">

                <label id="subject3_lbl" for="subject3">Third Subject</label>
                <input type="text" id="subject3" name="grade3" placeholder="Enter grade for third subject">

                <label for="sec-avg">Secondary Education Average</label>
                <input type="text" id="sec-avg" name="sec-avg" placeholder="Enter Secondary Education Average"><br/>

                <input class="btn" type="submit" value="Submit">
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
        document.getElementById('header').innerHTML = 'Your Profile';
    }

    function showSubmissions(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = 'Your Submissions';
    }

    function createSubmission(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = 'Select faculty where you want to submit';
    }

    function orderSubmission(index){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-submission').style.display = 'block';
        document.getElementById('header').innerHTML = 'Create submission for ' +
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

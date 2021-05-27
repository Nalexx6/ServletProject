<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admission committee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/index.css">
</head>
<body>
<form class="container">


    <div class="form-control">
        <input class="btn" type="button" value="Profile" onclick="showUserCredentials()">
        <input class="btn" type="button" value="Faculties" onclick="showFaculties()">
        <input class="btn" type="button" value="Users" onclick="showUsers()">
        <input class="btn" type="button" value="Create new faculty" onclick="createFaculty()">
        <h1 id="header" class="header">Faculties List</h1>
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

    <div id="faculties" style="display: block">
        <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
        <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
            <span id="fac-${f}">${faculties.get(f).name}</span>
            <input class="btn" type="button" value="Edit">
            <input class="btn" type="button" value="Delete">
            <br>
        </c:forEach>
    </div>

    <div id="users" style="display: none">
        <%--        <input class="btn" type="button" value="Create new submission" onclick="createSubmission()">--%>
        <c:set var = "users" scope="session" value="${sessionScope.users}"/>
        <c:forEach var="u" begin="0" end="${users.size() - 1}">
            <span id="user-${u}">${users.get(u).firstName} ${users.get(u).lastName}</span>
            <input class="btn" type="button" value="Block">
            <br>
        </c:forEach>
    </div>

    <div class="form-control" id="create-faculty" style="display: none">

        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="createFaculty"/>

            <label for="fac-name">Faculty Name</label>
            <input type="text" id="fac-name" name="name" placeholder="Enter name of new Faculty"><br/>

            <label for="st-amount">Students amount</label>
            <input type="text" id="st-amount" name="students_amount"
                   placeholder="Enter overall amount of students on new faculty"><br/>

            <label for="state-funded-amount">State funded amount</label>
            <input type="text" id="state-funded-amount" name="state_funded_amount"
                   placeholder="Enter state funded amount of students"><br/>

            <label for="subject1">First Subject</label>
            <input type="text" id="subject1" name="subject1_id" placeholder="Enter name of first subject">

            <label for="subject2">Second Subject</label>
            <input type="text" id="subject2" name="subject2_id" placeholder="Enter name of second subject">

            <label for="subject3">Third Subject</label>
            <input type="text" id="subject3" name="subject3_id" placeholder="Enter name of third subject">

            <input class="button btn" type="submit" value="Submit">
        </form>

    </div>
</form>



</body>
</html>

<script>

    function showUserCredentials(){
        document.getElementById('pers-inf').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('header').innerHTML = 'Your Profile';
    }

    function showFaculties(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('header').innerHTML = 'Faculties List';
    }

    function showUsers(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'block';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('header').innerHTML = 'Users List';
    }

    function createFaculty(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'block';
        document.getElementById('header').innerHTML = 'Create Faculty';
    }

    function getSelectedIndex(){
        return document.getElementById('fac-list').selectedIndex;


    }
</script>

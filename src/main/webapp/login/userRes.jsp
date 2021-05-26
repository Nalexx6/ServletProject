
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
    <form class="container">


        <div class="form-control">
            <input class="btn" type="button" value="Profile" onclick="showUserCredentials()">
            <input class="btn" type="button" value="Submissions" onclick="showSubmissions()">
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
            <input class="btn" type="button" value="Create new submission" onclick="createSubmission()">
            <p>You have no Submissions</p>
        </div>

        <div id="create-submission" style="display: none">
            <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
            <label for="fac-list">Select Faculty</label>
            <select id="fac-list">
                <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
                    <option>${faculties.get(f).name}</option>
                </c:forEach>
            </select><br>

<%--            <c:set var="selectedIndex">getSelectedIndex()</c:set>--%>
<%--            <c:out value="${selectedIndex}"/>--%>
            <c:set var="subjects" scope="session" value="${sessionScope.faculties.get(0).subjectWeights}"/>

            <c:forEach items="${subjects}" var="s">
                <label>${s.key.name} (weights ${s.value})</label>
                <input type="text" name="${s.key}" placeholder="Enter grade for ${s.key.name} "><br/>
            </c:forEach>

            <label for="sec-avg">Secondary Education Average</label>
            <input type="text" id="sec-avg" name="sec-avg" placeholder="Enter Secondary Education Agerage"><br/>

            <input class="btn" type="button" value="Submit">

        </div>
    </form>



</body>
</html>

<script>
    function showUserCredentials(){
        document.getElementById('pers-inf').style.display = 'block';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('header').innerHTML = 'Your Profile';
    }

    function showSubmissions(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'block';
        document.getElementById('create-submission').style.display = 'none';
        document.getElementById('header').innerHTML = 'Your Submissions';
    }

    function createSubmission(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('create-submission').style.display = 'block';
        document.getElementById('header').innerHTML = 'Create Submission';
    }

    function getSelectedIndex(){
        return document.getElementById('fac-list').selectedIndex;


    }
</script>

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
<div class="container">


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
            <span id="fac-${f}-name">${faculties.get(f).name}</span>
            <input type="hidden" id="fac-${f}-st-amount" value="${faculties.get(f).studentsAmount}"/>
            <input type="hidden" id="fac-${f}-st-funded-amount" value="${faculties.get(f).stateFundedAmount}"/>
            <input type="hidden" id="fac-${f}-subject1" value="${faculties.get(f).subjects.get(0).name}"/>
            <input type="hidden" id="fac-${f}-subject2" value="${faculties.get(f).subjects.get(1).name}"/>
            <input type="hidden" id="fac-${f}-subject3" value="${faculties.get(f).subjects.get(2).name}"/>
            <input class="btn" type="button" value="Edit"
                   onclick="editFaculty(${f});
                           <c:set var="editIndex" value="${f}"/> ">
            <input class="btn" type="button" style="background: red" value="Delete" onclick="deleteConfirm(${f})">
            <br>
        </c:forEach>

    </div>

    <div id="users" style="display: none">
        <c:set var = "users" scope="session" value="${sessionScope.users}"/>
        <c:forEach var="u" begin="0" end="${users.size() - 1}">
            <span id="user-${u}">${users.get(u).firstName} ${users.get(u).lastName}</span>
            <c:set var="color" value="${!users.get(u).role.equals('BLOCKED') ? 'red' : 'blue'}"/>
            <input class="btn" id="user-status-${u}" type="button"
                   style="background: ${color}" value="${!users.get(u).role.equals("BLOCKED") ? "Block" : "Unblock"}"
                    onclick="blockUser(${u})">
            <br>
        </c:forEach>
    </div>

    <div class="form-control" id="create-faculty" style="display: none">

        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" id="fac-command" name="command" value="createFaculty"/>
            <input type="hidden" id="edit-fac-index" name="editedFacIndex" value=""/>

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
            <input class="btn" type="button" id="edit-cancel" style="background: blue; visibility: hidden" value="Cancel"
                onclick="editCancel()">
        </form>

    </div>

    <div class="container message-box" id="delete-confirm" style="display: none; z-index: 999;">
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" id="op-command" name="command" value="deleteFaculty">
            <input type="hidden" id="op-index" name="opIndex" value="">

            <h1 id="message-head" class="header">Are you sure, you want to delete this faculty?</h1>
            <input class="button btn" type="submit" style="background: red" value="Yes">
            <input class="btn" id="op-cancel" type="button" value="Cancel" onclick="deleteCancel()">
        </form>
    </div>

</div>

</body>
</html>

<script>

    function showUserCredentials(){
        document.getElementById('pers-inf').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = 'Your Profile';

        clearFields();
    }

    function showFaculties(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = 'Faculties List';

        clearFields();
    }

    function showUsers(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'block';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = 'Users List';

        clearFields();
    }

    function createFaculty(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'block';
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = 'Create Faculty';
        document.getElementById('fac-command').value = 'createFaculty';

        clearFields();
    }

    function deleteConfirm(value){
        document.getElementById('delete-confirm').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('op-index').value = value;
        document.getElementById('op-command').value = 'deleteFaculty';
        document.getElementById('message-head').innerHTML = "Are you sure, you want to delete this faculty?";
        document.getElementById('op-cancel').onclick = deleteCancel;
    }

    function deleteCancel(){
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
    }

    function editFaculty(index){
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'block';
        document.getElementById('header').innerHTML = 'Edit Faculty';
        document.getElementById('fac-command').value = 'editFaculty';

        document.getElementById('edit-fac-index').value = index;
        document.getElementById('fac-name').value = document.getElementById('fac-' + index + '-name').innerText;
        document.getElementById('st-amount').value = document.getElementById('fac-' + index + '-st-amount').value;
        document.getElementById('state-funded-amount').value =
            document.getElementById('fac-' + index + '-st-funded-amount').value;
        document.getElementById('subject1').value = document.getElementById('fac-' + index + '-subject1').value;
        document.getElementById('subject2').value = document.getElementById('fac-' + index + '-subject2').value;
        document.getElementById('subject3').value = document.getElementById('fac-' + index + '-subject3').value;

        document.getElementById('edit-cancel').style.visibility = 'visible';

    }

    function editCancel(){
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('header').innerHTML = 'Edit Faculty';
        document.getElementById('fac-command').value = 'editFaculty';

        clearFields();
    }

    function clearFields(){
        document.getElementById('edit-fac-index').value = "";
        document.getElementById('fac-name').value = "";
        document.getElementById('st-amount').value = "";
        document.getElementById('state-funded-amount').value = "";
        document.getElementById('subject1').value = "";
        document.getElementById('subject2').value = "";
        document.getElementById('subject3').value = "";

        document.getElementById('edit-cancel').style.visibility = 'hidden';
    }

    function blockUser(value){

        document.getElementById('delete-confirm').style.display = 'block';
        document.getElementById('users').style.display = 'none';
        document.getElementById('op-index').value = value;
        document.getElementById('message-head').innerHTML = "Are you sure, you want to block this user?";
        document.getElementById('op-cancel').onclick = blockCancel;
        document.getElementById('op-command').value = 'blockUser';
    }
    
    function blockCancel(){
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('users').style.display = 'block';
    }

    function blockUser(value){

        if(document.getElementById('user-status-' + value).value === 'BLOCK') {
            document.getElementById('delete-confirm').style.display = 'block';
            document.getElementById('users').style.display = 'none';
            document.getElementById('op-index').value = value;
            document.getElementById('message-head').innerHTML = "Are you sure, you want to block this user?";
            document.getElementById('op-cancel').onclick = blockCancel;
            document.getElementById('op-command').value = 'blockUser';
        } else {
            document.getElementById('delete-confirm').style.display = 'block';
            document.getElementById('users').style.display = 'none';
            document.getElementById('op-index').value = value;
            document.getElementById('message-head').innerHTML = "Are you sure, you want to unblock this user?";
            document.getElementById('op-cancel').onclick = blockCancel;
            document.getElementById('op-command').value = 'unblockUser';
        }
    }

    function blockCancel(){
        document.getElementById('delete-confirm').style.display = 'none';
        document.getElementById('users').style.display = 'block';
    }

</script>

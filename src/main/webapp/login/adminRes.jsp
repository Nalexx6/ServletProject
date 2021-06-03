<%--
  Created by IntelliJ IDEA.
  User: Win10Pro
  Date: 5/16/2021
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" var="bundle" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="entry.name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/index.css">
</head>
<body>
<div class="container">

    <div id="locale-changer" class="form-control" style="margin: 0">
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="changeLocale">
            <input type="hidden" name="page-path" value="/login/adminRes.jsp">
            <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA">
        </form>
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="changeLocale">
            <input type="hidden" name="page-path" value="/login/adminRes.jsp">
            <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="EN">
        </form>
    </div>

    <div class="form-control">
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="logout">
            <input class="btn" style="background: red" type="submit" value="<fmt:message key="button.logout"/>">
        </form>
        <input class="btn" type="button" value="<fmt:message key="button.profile"/>" onclick="showUserCredentials()">
        <input class="btn" type="button" value="<fmt:message key="button.faculty"/>" onclick="showFaculties()">
        <input class="btn" type="button" value="<fmt:message key="button.users"/>" onclick="showUsers()">
        <input class="btn" type="button" value="<fmt:message key="button.checked_submissions"/>" onclick="showCheckedSubmissions()">
        <input class="btn" type="button" value="<fmt:message key="button.unchecked_submissions"/>" onclick="showUncheckedSubmissions()">
        <input class="btn" type="button" value="<fmt:message key="button.faculty.create"/>" onclick="createFaculty()">

        <h1 id="header" class="header"><fmt:message key="header.faculty"/></h1>
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

    <div id="faculties" style="display: block">
        <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
        <c:forEach var="f" begin="0" end="${faculties.size() - 1}">
            <span id="fac-${faculties.get(f).id}-name">${faculties.get(f).name}</span>
            <input type="hidden" id="fac-${faculties.get(f).id}-st-amount"
                   value="${faculties.get(f).studentsAmount}"/>
            <input type="hidden" id="fac-${faculties.get(f).id}-st-funded-amount"
                   value="${faculties.get(f).stateFundedAmount}"/>
            <input type="hidden" id="fac-${faculties.get(f).id}-subject1"
                   value="${faculties.get(f).subjects.get(0).name}"/>
            <input type="hidden" id="fac-${faculties.get(f).id}-subject2"
                   value="${faculties.get(f).subjects.get(1).name}"/>
            <input type="hidden" id="fac-${faculties.get(f).id}-subject3"
                   value="${faculties.get(f).subjects.get(2).name}"/>
            <input class="btn" type="button" value="<fmt:message key="button.faculty.edit"/>"
                   onclick="editFaculty(${faculties.get(f).id});
                           <c:set var="editIndex" value="${faculties.get(f).id}"/> ">
            <input class="btn" type="button" style="background: red" value="<fmt:message key="button.faculty.delete"/>"
                   onclick="deleteConfirm(${faculties.get(f).id})">
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
    
    <div class="form-control" id="unchecked-submissions" style="display: none">
            <c:set var = "submissions" scope="session" value="${sessionScope.submissions}"/>
            <c:if test="${submissions.size() != 0}">
                <c:forEach var="s" begin="0" end="${submissions.size() - 1}">
                    <c:if test="${!submissions.get(s).checked}">
                        <span id="submission-${s}">${submissions.get(s).user.firstName}</span>
                        <span id="submission-${s}">${submissions.get(s).user.lastName}</span>
                        <span id="submission-${s}">${submissions.get(s).faculty.name}</span>

                        <input class="btn" type="button" style="background: blue" value="<fmt:message key="submission.check"/>"
                               onclick="confirmSubmissionCheck(${s})"/>
                        <br>
                    </c:if>
                </c:forEach>
            </c:if>
    </div>

    <div class="form-control" id="submissions" style="display: none">
            <c:set var = "submissions" scope="session" value="${sessionScope.submissions}"/>
            <c:if test="${submissions.size() != 0}">
                <c:forEach var="s" begin="0" end="${submissions.size() - 1}">
                    <c:if test="${submissions.get(s).checked}">
                    <span id="submission-${s}">${submissions.get(s).user.firstName}</span>
                    <span id="submission-${s}">${submissions.get(s).user.lastName}</span>
                    <span id="submission-${s}">${submissions.get(s).faculty.name}</span>

                    <input class="btn" type="button" style="background: green" value="<fmt:message key="submission.checked"/>"/>
                    <br>
                    </c:if>
                </c:forEach>
            </c:if>
    </div>

    <div class="form-control" id="create-faculty" style="display: none">

        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" id="fac-command" name="command" value="createFaculty"/>
            <input type="hidden" id="edit-fac-index" name="editedFacIndex" value=""/>
            <h2 id="error-message" style="color: red; text-align: center">${sessionScope.message}</h2>
            <input type="hidden" id="fac-error-index" value="${sessionScope.facIndex}">
            <label for="fac-name"><fmt:message key="faculty.label.name"/></label>
            <input type="text" id="fac-name" name="name"
                   placeholder="<fmt:message key="faculty.placeholder.name"/>"><br/>

            <label for="st-amount"><fmt:message key="faculty.label.students_amount"/></label>
            <input type="text" id="st-amount" name="students_amount"
                   placeholder="<fmt:message key="faculty.placeholder.students_amount"/>"><br/>

            <label for="state-funded-amount"><fmt:message key="faculty.label.state_funded_amount"/></label>
            <input type="text" id="state-funded-amount" name="state_funded_amount"
                   placeholder="<fmt:message key="faculty.placeholder.state_funded_amount"/>"><br/>

            <label for="subject1"><fmt:message key="faculty.label.subject1"/></label>
            <input type="text" id="subject1" name="subject1_id"
                   placeholder="<fmt:message key="faculty.placeholder.subject1"/>">

            <label for="subject2"><fmt:message key="faculty.label.subject2"/></label>
            <input type="text" id="subject2" name="subject2_id"
                   placeholder="<fmt:message key="faculty.placeholder.subject2"/>">

            <label for="subject3"><fmt:message key="faculty.label.subject3"/></label>
            <input type="text" id="subject3" name="subject3_id"
                   placeholder="<fmt:message key="faculty.placeholder.subject3"/>">

            <input class="button btn" type="submit" value="<fmt:message key="button.submit"/>">
            <input class="btn" type="button" id="edit-cancel" style="background: blue; visibility: hidden" value="<fmt:message key="button.cancel"/>"
                onclick="editCancel()">
        </form>

    </div>

    <div class="container message-box" id="op-confirm" style="display: none; z-index: 999;">
        <form method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" id="op-command" name="command" value="deleteFaculty">
            <input type="hidden" id="op-index" name="opIndex" value="">

            <h1 id="message-head" class="header"><fmt:message key="header.faculty.delete"/></h1>
            <input class="button btn" type="submit" style="background: red" value="<fmt:message key="button.confirm"/>">
            <input class="btn" id="op-cancel" type="button" value="<fmt:message key="button.cancel"/>" onclick="deleteCancel()">
        </form>
    </div>

</div>

</body>
</html>

<script>

    window.onload = init;

    function init(){
        if(document.getElementById("error-message").innerText !== ""){
            if(document.getElementById("fac-error-index").value === "") {
                createFaculty();
            } else {
                editFaculty(document.getElementById("fac-error-index").value);
            }
        } else {
            showFaculties();
        }
    }

    function showUserCredentials(){
        document.getElementById('pers-inf').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.profile"/>';

        clearFields();
    }

    function showFaculties(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.faculty"/>';

        clearFields();
    }

    function showUsers(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'block';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.users"/>';

        clearFields();
    }

    function showCheckedSubmissions(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('submissions').style.display = 'block';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.checked_submissions"/>';

        clearFields();
    }

    function showUncheckedSubmissions(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'block';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.unchecked_submissions"/>';

        clearFields();
    }

    function createFaculty(){
        document.getElementById('pers-inf').style.display = 'none';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('users').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'block';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('submissions').style.display = 'none';
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('header').innerHTML = '<fmt:message key="header.faculty.create"/>';
        document.getElementById('fac-command').value = 'createFaculty';

        clearFields();
    }

    function deleteConfirm(value){
        document.getElementById('op-confirm').style.display = 'block';
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('op-index').value = value;
        document.getElementById('op-command').value = 'deleteFaculty';
        document.getElementById('message-head').innerHTML = "<fmt:message key="header.faculty.delete"/>";
        document.getElementById('op-cancel').onclick = deleteCancel;
    }

    function deleteCancel(){
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('faculties').style.display = 'block';
    }

    function editFaculty(index){
        document.getElementById('faculties').style.display = 'none';
        document.getElementById('create-faculty').style.display = 'block';
        document.getElementById('header').innerHTML = '<fmt:message key="header.faculty.edit"/>';
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
        document.getElementById('header').innerHTML = '<fmt:message key="header.faculty"/>';
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

    function blockCancel(){
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('users').style.display = 'block';
    }

    function blockUser(value){

        if(document.getElementById('user-status-' + value).value === 'Block') {
            document.getElementById('op-confirm').style.display = 'block';
            document.getElementById('users').style.display = 'none';
            document.getElementById('op-index').value = value;
            document.getElementById('message-head').innerHTML = "<fmt:message key="header.user.block"/>";
            document.getElementById('op-cancel').onclick = blockCancel;
            document.getElementById('op-command').value = 'blockUser';
        } else {
            document.getElementById('op-confirm').style.display = 'block';
            document.getElementById('users').style.display = 'none';
            document.getElementById('op-index').value = value;
            document.getElementById('message-head').innerHTML = "<fmt:message key="header.user.unblock"/>";
            document.getElementById('op-cancel').onclick = blockCancel;
            document.getElementById('op-command').value = 'unblockUser';
        }
    }

    function confirmSubmissionCheck(value){
        document.getElementById('op-confirm').style.display = 'block';
        document.getElementById('unchecked-submissions').style.display = 'none';
        document.getElementById('op-index').value = value;
        document.getElementById('message-head').innerHTML = "<fmt:message key="header.submission.check"/>";
        document.getElementById('op-cancel').onclick = checkCancel;
        document.getElementById('op-command').value = 'checkSubmission';
    }

    function checkCancel(){
        document.getElementById('op-confirm').style.display = 'none';
        document.getElementById('unchecked-submissions').style.display = 'block';
    }

</script>

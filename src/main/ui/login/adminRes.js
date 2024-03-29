

function init(){

    if(document.getElementById("message-key").value !== ""){
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
    document.getElementById('subjects').style.display = 'none';
    document.getElementById('create-faculty').style.display = 'none';
    document.getElementById('unchecked-submissions').style.display = 'none';
    document.getElementById('submissions').style.display = 'none';
    document.getElementById('op-confirm').style.display = 'none';
    document.getElementById('header').innerHTML = '<fmt:message key="header.profile"/>';

    clearFields();
}

function showSubjects(){
    document.getElementById('pers-inf').style.display = 'none';
    document.getElementById('faculties').style.display = 'none';
    document.getElementById('users').style.display = 'none';
    document.getElementById('subjects').style.display = 'block';
    document.getElementById('create-faculty').style.display = 'none';
    document.getElementById('unchecked-submissions').style.display = 'none';
    document.getElementById('submissions').style.display = 'none';
    document.getElementById('op-confirm').style.display = 'none';
    document.getElementById('header').innerHTML = '<fmt:message key="header.subjects"/>';
}

function showFaculties(){
    document.getElementById('pers-inf').style.display = 'none';
    document.getElementById('faculties').style.display = 'block';
    document.getElementById('users').style.display = 'none';
    document.getElementById('subjects').style.display = 'none';
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
    document.getElementById('subjects').style.display = 'none';
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
    document.getElementById('subjects').style.display = 'none';
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
    document.getElementById('subjects').style.display = 'none';
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
    document.getElementById('subjects').style.display = 'none';
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
    document.getElementById('st-amount').value = document.getElementById('fac-' + index + '-st-amount').innerText;
    document.getElementById('state-funded-amount').value =
    document.getElementById('fac-' + index + '-st-funded-amount').innerText;
    document.getElementById('subject1').value = document.getElementById('fac-' + index + '-subject1').innerText;
    document.getElementById('subject2').value = document.getElementById('fac-' + index + '-subject2').innerText;
    document.getElementById('subject3').value = document.getElementById('fac-' + index + '-subject3').innerText;

    document.getElementById('edit-cancel').style.visibility = 'visible';

}

function editCancel(){
    document.getElementById('faculties').style.display = 'block';
    document.getElementById('create-faculty').style.display = 'none';
    document.getElementById('header').innerHTML = '<fmt:message key="header.faculty"/>';
    document.getElementById('fac-command').value = 'editFaculty';

    clearFields();
}

function showSubmissionsForFaculty(index){
    document.getElementById('faculties').style.display = 'none';
    document.getElementById("faculty-" + index + "-submissions").style.display = 'block';
    document.getElementById('header').innerHTML = '<fmt:message key="header.faculty.submissions"/>' + ' ' +
    document.getElementById('fac-' + index + '-name').innerText;

}

function closeSubmissionsForFaculty(index){
    document.getElementById('faculties').style.display = 'block';
    document.getElementById("faculty-" + index + "-submissions").style.display = 'none';
    document.getElementById('header').innerHTML = '<fmt:message key="header.faculty"/>';
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

function blockCancel(){
    document.getElementById('op-confirm').style.display = 'none';
    document.getElementById('users').style.display = 'block';
}

function confirmSubmissionCheck(index){
    document.getElementById('op-confirm').style.display = 'block';
    document.getElementById('unchecked-submissions').style.display = 'none';
    document.getElementById('op-index').value = index;
    document.getElementById('message-head').innerHTML = "<fmt:message key="header.submission.check"/>";
    document.getElementById('op-cancel').onclick = checkCancel;
    document.getElementById('op-command').value = 'checkSubmission';
}

function checkCancel(){
    document.getElementById('op-confirm').style.display = 'none';
    document.getElementById('unchecked-submissions').style.display = 'block';
}

function confirmFinalization(){
    showFaculties();
    document.getElementById('op-confirm').style.display = 'block';
    document.getElementById('faculties').style.display = 'none';
    document.getElementById('message-head').innerHTML = "<fmt:message key="header.finalize"/>";
    document.getElementById('op-cancel').onclick = finalizationCancel;
    document.getElementById('op-command').value = 'finalizeCertificate';
}

function finalizationCancel(){
    showFaculties();
}


const adminRes = () => (
    <html lang="${sessionScope.locale}">
    <head>
        <title><fmt:message key="entry.name"/></title>
    </head>
    <body>
    <div class="container">


        <div id="locale-changer" class="form-control" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale"/>
                <input type="hidden" name="page-path" value="/login/adminRes.jsp"/>
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA"/>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale"/>
                <input type="hidden" name="page-path" value="/login/adminRes.jsp"/>
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="EN"/>
            </form>
        </div>
        <c:if test="${applicationScope.finalized}">
            <h1 class="header"><fmt:message key="header.finalized"/></h1>
        </c:if>
        <div class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="logout"/>
                <input class="btn" style="background: red" type="submit" value="<fmt:message key="button.logout"/>"/>
            </form>
            <input class="btn" type="button" value="<fmt:message key="button.profile"/>" onclick="showUserCredentials()">
            <input class="btn" type="button" value="<fmt:message key="button.faculty"/>" onclick="showFaculties()">
            <input class="btn" type="button" value="<fmt:message key="button.users"/>" onclick="showUsers()">
            <input class="btn" type="button" value="<fmt:message key="button.subjects"/>" onclick="showSubjects()">
            <input class="btn" type="button" value="<fmt:message key="button.checked_submissions"/>"
                   onclick="showCheckedSubmissions()">
            <c:if test="${!applicationScope.finalized}">
                <input class="btn" type="button" value="<fmt:message key="button.unchecked_submissions"/>"
                       onclick="showUncheckedSubmissions()">
                <input class="btn" type="button" value="<fmt:message key="button.faculty.create"/>" onclick="createFaculty()">
                <input class="btn" style="background: blue" type="button" value="<fmt:message key="button.finalize"/>"
                       onclick="confirmFinalization()">
            </c:if>
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

        <div id="subjects" style="display: none">
            <c:set var="subjects" scope="session" value="${sessionScope.subjects}"/>
            <table>
                <tr>
                    <th><input class="btn" type="button" value="<fmt:message key="subject.name"/>"/></th>
                </tr>
                <c:forEach var="s" items="${subjects}">
                    <tr>
                        <th><span>${s.name}</span></th>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="faculties" style="display: block">
            <c:set var = "faculties" scope="session" value="${sessionScope.faculties}"/>
            <table>
                <tr>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.alphabetSort}">
                            <input type="hidden" name="page-path" value="/login/adminRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.name"/>"/>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.studentSort}">
                            <input type="hidden" name="page-path" value="/login/adminRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.students_amount"/>"/>
                        </form>
                    </th>
                    <th>
                        <form method="post" action="${pageContext.request.contextPath}/servlet">
                            <input type="hidden" name="command" value="sortFaculties"/>
                            <input type="hidden" name="sortType" value="${sessionScope.stateFundedSort}">
                            <input type="hidden" name="page-path" value="/login/adminRes.jsp">
                            <input class="btn" type="submit" value="<fmt:message key="faculty.label.state_funded_amount"/>"/>
                        </form>
                    </th>
                    <th><fmt:message key="faculty.label.subject1"/></th>
                    <th><fmt:message key="faculty.label.subject2"/></th>
                    <th><fmt:message key="faculty.label.subject3"/></th>
                </tr>
                <c:forEach var="f" items="${faculties}" >
                    <tr>
                    <th><span id="fac-${f.id}-name">${f.name}</span></th>
                    <th><span id="fac-${f.id}-st-amount">${f.studentsAmount}</span></th>
                    <th><span id="fac-${f.id}-st-funded-amount">${f.stateFundedAmount}</span></th>
                    <th><span id="fac-${f.id}-subject1">${f.subjects.get(0).name}</span></th>
                    <th><span id="fac-${f.id}-subject2">${f.subjects.get(1).name}</span></th>
                    <th><span id="fac-${f.id}-subject3">${f.subjects.get(2).name}</span></th>
                    <c:if test="${!applicationScope.finalized}">
                        <th style="border-bottom: 0;"><input class="btn" type="button"
                                 value="<fmt:message key="button.faculty.edit"/>" onclick="editFaculty(${f.id})"/></th>
                        <th style="border-bottom: 0;"><input class="btn" type="button" style="background: red;"
                                value="<fmt:message key="button.faculty.delete"/>" onclick="deleteConfirm(${f.id})"></th>
                    </c:if>
                        <th style="border-bottom: 0;"><input class="btn" type="button" style="background: blue;"
                                value="<fmt:message key="button.faculty.submissions"/>"
                                onclick="showSubmissionsForFaculty(${f.id})"></th>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <c:forEach var="f" items="${faculties}">
            <div id="faculty-${f.id}-submissions" style="display: none">
                <fmt:message key="button.close" var="closeButton"/>
                <input class="btn" type="button" value="${closeButton}"
                        onclick="closeSubmissionsForFaculty(${f.id})"/>
                <table>
                    <tr>
                        <th><fmt:message key="user.label.firstName"/></th>
                        <th><fmt:message key="user.label.lastName"/></th>
                        <th><fmt:message key="faculty.label.name"/></th>
                        <th><fmt:message key="faculty.label.subject1"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject2"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject3"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                    </tr>
                    <c:forEach var="s" items="${f.submissions}">
                        <tr>
                            <th><span>${s.user.firstName}</span></th>
                            <th><span>${s.user.lastName}</span></th>
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
                                            <c:set var="color" value="green"/>
                                            <fmt:message key="submission.checked" var="status"/>
                                        </c:when>
                                        <c:when test="${!s.checked}">
                                            <c:set var="color" value="blue"/>
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
        </c:forEach>

        <div id="users" style="display: none">
            <c:set var = "users" scope="session" value="${sessionScope.users}"/>
            <table>
                <tr>
                    <th><fmt:message key="user.label.login"/></th>
                    <th><fmt:message key="user.label.firstName"/></th>
                    <th><fmt:message key="user.label.lastName"/></th>
                    <th><fmt:message key="user.label.email"/></th>
                </tr>
            <c:forEach var="u" items="${users}">
                <c:if test="${u.role != 'ADMIN'}">
                    <tr>
                        <th><span id="user-${u.id}">${u.login}</span></th>
                        <th><span id="user-${u.id}">${u.firstName}</span></th>
                        <th><span id="user-${u.id}">${u.lastName}</span></th>
                        <th><span id="user-${u.id}">${u.email}</span></th>

                        <c:if test="${!applicationScope.finalized}">
                            <c:choose>
                                <c:when test="${u.role == 'BLOCKED'}">
                                    <c:set var="color" value="blue"/>
                                    <fmt:message key="user.unblock" var="status"/>
                                </c:when>
                                <c:when test="${u.role != 'BLOCKED'}">
                                    <c:set var="color" value="red"/>
                                    <fmt:message key="user.block" var="status"/>
                                </c:when>
                            </c:choose>
                            <th style="border-bottom: 0;"><input class="btn" id="user-status-${u.id}" type="button"
                                 style="background: ${color}" value="${status}"
                                     onclick="blockUser(${u.id})">
                            </th>
                        </c:if>
                    </tr>
                </c:if>
            </c:forEach>
            </table>
        </div>

        <div class="form-control" id="unchecked-submissions" style="display: none">
            <c:set var = "submissions" scope="session" value="${sessionScope.submissions}"/>
            <c:if test="${submissions.size() != 0}">
                <table>
                    <tr>
                        <th><fmt:message key="user.label.firstName"/></th>
                        <th><fmt:message key="user.label.lastName"/></th>
                        <th><fmt:message key="faculty.label.name"/></th>
                        <th><fmt:message key="faculty.label.subject1"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject2"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject3"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                    </tr>
                    <c:forEach var="s" items="${submissions}">
                        <c:if test="${!s.checked}">
                            <tr>
                                <th><span id="submission-${s.id}">${s.user.firstName}</span></th>
                                <th><span id="submission-${s.id}">${s.user.lastName}</span></th>
                                <th><span id="submission-${s.id}">${s.faculty.name}</span></th>
                                <th><span>${s.faculty.subjects.get(0).name}</span></th>
                                <th><span>${s.grades.get(0)}</span></th>
                                <th><span>${s.faculty.subjects.get(1).name}</span></th>
                                <th><span>${s.grades.get(1)}</span></th>
                                <th><span>${s.faculty.subjects.get(2).name}</span></th>
                                <th><span>${s.grades.get(2)}</span></th>

                                <th style="border-bottom: 0;"><input class="btn" type="button" style="background: blue"
                                     value="<fmt:message key="submission.check"/>"
                                     onclick="confirmSubmissionCheck(${s.id})"/></th>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
        </div>

        <div class="form-control" id="submissions" style="display: none">
            <c:set var = "submissions" scope="session" value="${sessionScope.submissions}"/>
            <c:if test="${submissions.size() != 0}">
                <table>
                    <tr>
                        <th><fmt:message key="user.label.firstName"/></th>
                        <th><fmt:message key="user.label.lastName"/></th>
                        <th><fmt:message key="faculty.label.name"/></th>
                        <th><fmt:message key="faculty.label.subject1"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject2"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                        <th><fmt:message key="faculty.label.subject3"/></th>
                        <th><fmt:message key="submission.label.grade"/></th>
                    </tr>
                    <c:forEach var="s" items="${submissions}">
                        <c:if test="${s.checked}">
                            <tr>
                                <th><span id="submission-${s.id}">${s.user.firstName}</span></th>
                                <th><span id="submission-${s.id}">${s.user.lastName}</span></th>
                                <th><span id="submission-${s.id}">${s.faculty.name}</span></th>
                                <th><span>${s.faculty.subjects.get(0).name}</span></th>
                                <th><span>${s.grades.get(0)}</span></th>
                                <th><span>${s.faculty.subjects.get(1).name}</span></th>
                                <th><span>${s.grades.get(1)}</span></th>
                                <th><span>${s.faculty.subjects.get(2).name}</span></th>
                                <th><span>${s.grades.get(2)}</span></th>

                                <th style="border-bottom: 0;"><input class="btn" type="button" style="background: green"
                                           value="<fmt:message key="submission.checked"/>"/></th>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
        </div>

        <div class="form-control" id="create-faculty" style="display: none">

            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" id="fac-command" name="command" value="createFaculty"/>
                <input type="hidden" id="edit-fac-index" name="editedFacIndex" value=""/>

                <c:if test="${sessionScope.message != null}">
                    <h2 id="error-message" style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h2>
                </c:if>
                <input type="hidden" id="message-key" value="${sessionScope.message}">
                <input type="hidden" id="fac-error-index" value="${sessionScope.facIndex}">

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.name"/></h4>
                </c:if>
                <label for="fac-name"><fmt:message key="faculty.label.name"/></label>
                <input type="text" id="fac-name" name="name"
                       placeholder="<fmt:message key="faculty.placeholder.name"/>"><br/>

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.students"/></h4>
                </c:if>
                <label for="st-amount"><fmt:message key="faculty.label.students_amount"/></label>
                <input type="text" id="st-amount" name="students_amount"
                       placeholder="<fmt:message key="faculty.placeholder.students_amount"/>"><br/>

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.students"/></h4>
                </c:if>
                <label for="state-funded-amount"><fmt:message key="faculty.label.state_funded_amount"/></label>
                <input type="text" id="state-funded-amount" name="state_funded_amount"
                       placeholder="<fmt:message key="faculty.placeholder.state_funded_amount"/>"><br/>

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.subject"/></h4>
                </c:if>
                <label for="subject1"><fmt:message key="faculty.label.subject1"/></label>
                <input type="text" id="subject1" name="subject1_id"
                       placeholder="<fmt:message key="faculty.placeholder.subject1"/>">

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.subject"/></h4>
                </c:if>
                <label for="subject2"><fmt:message key="faculty.label.subject2"/></label>
                <input type="text" id="subject2" name="subject2_id"
                       placeholder="<fmt:message key="faculty.placeholder.subject2"/>">

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.subject"/></h4>
                </c:if>
                <label for="subject3"><fmt:message key="faculty.label.subject3"/></label>
                <input type="text" id="subject3" name="subject3_id"
                       placeholder="<fmt:message key="faculty.placeholder.subject3"/>">

                <c:if test="${sessionScope.message != 'message.faculty.exists' && sessionScope.message != null}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.faculty.invalid.name"/></h4>
                </c:if>
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
                <input class="btn" id="op-cancel" type="button" value="<fmt:message key="button.cancel"/>"
                       onclick="deleteCancel()">
            </form>
        </div>

    </div>

    </body>
    </html>
)




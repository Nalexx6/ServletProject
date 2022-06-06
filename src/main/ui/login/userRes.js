 function init(){
    if(document.getElementById("message-key").value !== ""){
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
    document.getElementById("sec_education_avg").value = "";
}

const userRes = () => (
    <html lang="${sessionScope.locale}">
    <head>
        <title><fmt:message key="entry.name"/> </title>

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
                        <tr>
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
                    <c:forEach var="f" items="${faculties}">
                        <tr>
                            <th><span id="fac-${f.id}-name">${f.name}</span></th>
                            <th><span id="fac-${f.id}-st-amount">${f.studentsAmount}</span></th>
                            <th><span id="fac-${f.id}-st-funded-amount">${f.stateFundedAmount}</span></th>
                            <th><span id="fac-${f.id}-subject1">${f.subjects.get(0).name}</span></th>
                            <th><span id="fac-${f.id}-subject2">${f.subjects.get(1).name}</span></th>
                            <th><span id="fac-${f.id}-subject3">${f.subjects.get(2).name}</span></th>
                            <th style="border-bottom: 0;"><input class="btn" type="button"
                                 value="<fmt:message key="submission.create.order"/>" onclick="orderSubmission(${f.id})"/></th>
                        </tr>
                    </c:forEach>

                </table>
            </div>

            <div class="form-control" id="create-submission" style="display: none" >
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                    <input type="hidden" name="command" value="createSubmission">
                    <input type="hidden" id="sub-fac-index" name="facultyIndex" value="0">
                    <c:if test="${sessionScope.message != null}">
                        <h2 id="error-message" style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h2>
                    </c:if>
                    <input type="hidden" id="message-key" value="${sessionScope.message}">
                    <input type="hidden" id="fac-error-index" value="${sessionScope.facIndex}">

                    <c:if test="${sessionScope.message != null}">
                        <h4 style="color: red; text-align: center"><fmt:message key="message.submission.grade"/></h4>
                    </c:if>
                    <label id="subject1_lbl" for="subject1"></label>
                    <input type="text" id="subject1" name="grade1"
                           placeholder="<fmt:message key="submission.placeholder.subject1"/>">

                    <c:if test="${sessionScope.message != null}">
                        <h4 style="color: red; text-align: center"><fmt:message key="message.submission.grade"/></h4>
                    </c:if>
                    <label id="subject2_lbl" for="subject2"></label>
                    <input type="text" id="subject2" name="grade2"
                           placeholder="<fmt:message key="submission.placeholder.subject2"/>">

                    <c:if test="${sessionScope.message != null}">
                        <h4 style="color: red; text-align: center"><fmt:message key="message.submission.grade"/></h4>
                    </c:if>
                    <label id="subject3_lbl" for="subject3"></label>
                    <input type="text" id="subject3" name="grade3"
                           placeholder="<fmt:message key="submission.placeholder.subject3"/>">

                    <c:if test="${sessionScope.message != null}">
                        <h4 style="color: red; text-align: center"><fmt:message key="message.submission.sec_avg"/></h4>
                    </c:if>
                    <label for="sec_education_avg"><fmt:message key="submission.label.sec_avg"/></label>
                    <input type="text" id="sec_education_avg" name="sec_education_avg"
                           placeholder="<fmt:message key="submission.placeholder.sec_avg"/>"><br/>

                    <input class="btn" type="submit" value="<fmt:message key="button.submit"/>">
                </form>
            </div>
        </div>



    </body>
    </html>
)
 export default userRes
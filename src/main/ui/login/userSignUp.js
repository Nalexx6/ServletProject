const userSignUp = () => (
    <html lang="${sessionScope.locale}">
    <head>
    <title><fmt:message key="signUp.label"/></title>
    </head>
    <body>

        <div id="locale-changer" class="form-control" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale"/>
                <input type="hidden" name="page-path" value="/login/userSignUp.jsp"/>
                <input class="btn" style="background: lightgray" type="submit" name="locale" value="UA"/>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale"/>
                <input type="hidden" name="page-path" value="/login/userSignUp.jsp"/>
                <input class="btn" style="background: lightgray" type="submit" name="locale" value="EN"/>
            </form>
        </div>

        <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="signUp"/>


            <div class="form-control">
                <h1 class="header"><fmt:message key="header.signUp"/></h1>
                <c:if test="${sessionScope.message != null}">
                    <h2 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h2>
                </c:if>
                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.login"/></h4>
                </c:if>
                <label for="login-input"><fmt:message key="user.label.login"/></label>
                <input id="login-input" type="text" name="login"
                       placeholder="<fmt:message key="signUp.placeholder.login"/>"/><br/>
                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.password"/></h4>
                </c:if>
                <label for="pass-input"><fmt:message key="user.label.password"/></label>
                <input id="pass-input" type="text" name="password"
                       placeholder="<fmt:message key="signUp.placeholder.password"/>"/><br/>
                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.fName"/></h4>
                </c:if>
                <label for="fName"><fmt:message key="user.label.firstName"/></label>
                <input id="fName" type="text" name="first_name"
                       placeholder="<fmt:message key="signUp.placeholder.firstName"/>"/><br/>
                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.lName"/></h4>
                </c:if>
                <label for="lName"><fmt:message key="user.label.lastName"/></label>
                <input id="lName" type="text" name="last_name"
                       placeholder="<fmt:message key="signUp.placeholder.lastName"/>"/><br/>

                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.email"/></h4>
                </c:if>
                <label for="email-input"><fmt:message key="user.label.email"/></label>
                <input id="email-input" type="email" name="email"
                       placeholder="<fmt:message key="signUp.placeholder.email"/>"/><br/>

                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.city"/></h4>
                </c:if>
                <label for="city-input"><fmt:message key="user.label.city"/></label>
                <input id="city-input" type="text" name="city"
                       placeholder="<fmt:message key="signUp.placeholder.city"/>"/><br/>

                <c:if test="${sessionScope.message == 'message.signUp.invalid'}">
                    <h4 style="color: red; text-align: center"><fmt:message key="message.signUp.invalid.region"/></h4>
                </c:if>
                <label for="region-input"><fmt:message key="user.label.region"/></label>
                <input id="region-input" type="text" name="region"
                       placeholder="<fmt:message key="signUp.placeholder.region"/>"/><br/>

                <label for="inst"><fmt:message key="user.label.institution"/></label>
                <input id="inst" type="text" name="institution"
                       placeholder="<fmt:message key="signUp.placeholder.institution"/>"/><br/>

                <input class="button btn" type="submit" value="<fmt:message key="button.signUp"/>"/>
                <a class="link" href="${pageContext.request.contextPath}/app.js"><fmt:message key="entry.backToMain"/></a>
            </div>
        </form>

    </body>
    </html>
)
export default userSignUp

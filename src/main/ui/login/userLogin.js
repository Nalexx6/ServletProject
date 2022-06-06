const userLogin = () => (
    <html lang="${sessionScope.locale}">
    <head>
        <title><fmt:message key="login.label"/></title>
        <link rel="stylesheet" href="index.css">
    </head>
    <body>

        <div id="locale-changer" class="form-control" style="margin: 0">
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login/userLogin.jsp">
                <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA">
            </form>
            <form method="post" action="${pageContext.request.contextPath}/servlet">
                <input type="hidden" name="command" value="changeLocale">
                <input type="hidden" name="page-path" value="/login/userLogin.jsp">
                <input class="btn" style="background: lightgray; width: 50px;" type="submit" name="locale" value="EN">
            </form>
        </div>

        <form class="container " method="post" action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="login"/>
            <div class="form-control">
                <h1 class="header"><fmt:message key="header.login"/></h1>
                <c:if test="${sessionScope.message != null}">
                    <h2 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h2>
                </c:if>
                <label for="login-input"><fmt:message key="user.label.login"/></label>
                <input id="login-input" type="text" name="login"
                       placeholder="<fmt:message key="login.login.placeholder"/>"><br/>

                <label for="pass-input"><fmt:message key="user.label.password"/></label>
                <input id="pass-input" type="text" name="password"
                       placeholder="<fmt:message key="login.password.placeholder"/>"><br/>

                <input class="button btn" type="submit" value="<fmt:message key="button.login"/>">
                <a class="link" href="${pageContext.request.contextPath}/app.js"><fmt:message key="entry.backToMain"/></a>
            </div>
        </form>

    </body>
    </html>
)
export default userLogin
const app = () => (

    <html lang="${sessionScope.locale}">
    <head>
        <title>
            <fmt:message key="entry.name"/>
        </title>
    </head>
    <body>
        <div class="container">
            <div id="locale-changer" class="form-control" style="margin: 0">
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                    <input type="hidden" name="command" value="changeLocale"/>
                    <input type="hidden" name="page-path" value="/index.jsp"/>
                    <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="UA"/>
                </form>
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                    <input type="hidden" name="command" value="changeLocale"/>
                    <input type="hidden" name="page-path" value="/index.jsp"/>
                    <input class="btn" style="background: lightgray; width: 50px" type="submit" name="locale" value="EN"/>
                </form>
            </div>
            <c:if test="${sessionScope.message != null}">
                <h4 style="color: red; text-align: center"><fmt:message key="${sessionScope.message}"/></h4>
            </c:if>
            <h2 class="header">
                <fmt:message key="entry.name"/> <br/>
            </h2>

            <br/>
            <a href="${pageContext.request.contextPath}/login/userLogin.js" style="font-size: 2.0em">
                <fmt:message key="login.label"/>
            </a>
            <c:if test="${!applicationScope.finalized}">
            <br/>
            <a href="${pageContext.request.contextPath}/login/userSignUp.js" style="font-size: 2.0em">
                <fmt:message key="signUp.label"/>
            </a>
            <br/>
            </c:if>
        </div>
    </body>
    </html>
)
export default app
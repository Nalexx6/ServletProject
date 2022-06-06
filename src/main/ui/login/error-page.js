const errorPage = () => {
    <html>
    <head>
        <title>Error</title>
    </head>
    <body>

        <h1>Error</h1>
        <c:out value="${requestScope.message}"/>
        <a class="link" href="${pageContext.request.contextPath}/app.js">Back to main page</a>

    </body>
    </html>
}

export default errorPage
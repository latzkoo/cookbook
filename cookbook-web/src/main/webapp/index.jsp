<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/head.jsp" %>
<body class="d-flex flex-column h-100 bg-light">
<%@ include file="layouts/header.jsp" %>

<main role="main" class="flex-shrink-0">
    <section class="content my-5">
        <div class="container m-auto">
            <c:forEach items="${requestScope.measures}" var="measure">
                <div><c:out value="${measure.getName()}"/></div>
            </c:forEach>
        </div>
    </section>
</main>

<%@ include file="layouts/footer.jsp" %>
<%@ include file="includes/scripts.jsp" %>
</body>
</html>
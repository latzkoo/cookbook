<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="container-header py-2">
        <nav class="navbar navbar-expand-md px-0">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <span class="icon"></span><span class="label">Szakácskönyv</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Menü megnyitása">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/recipes">Receptek</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/menus">Menük</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/shopping">Bevásárlás</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cooking">Főzés</a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>

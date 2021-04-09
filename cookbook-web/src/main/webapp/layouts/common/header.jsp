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
                    <li class="nav-item dropdown ml-md-3">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">Keresés</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown02">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/search">Egyszerű keresés</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/search/stock">Alapanyag készlet szerint</a>
                        </div>
                    </li>
                    <li class="nav-item ml-md-4">
                        <a class="nav-link" href="${pageContext.request.contextPath}/recipes">Receptek</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/menus">Menük</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/materials">Alapanyagok</a></li>
                    <li class="nav-item ml-md-3">
                        <a class="nav-link" href="${pageContext.request.contextPath}/shopping">Bevásárlás</a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>

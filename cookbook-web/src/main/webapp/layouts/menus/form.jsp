<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="../common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row">
                    <div class="col-12 p-0"><h3><a href="${pageContext.request.contextPath}/menus">Menük</a> ›
                        <span class="small"><c:choose><c:when test="${!empty requestScope.content}">módosítás</c:when><c:otherwise>új hozzáadása</c:otherwise></c:choose></span></h3></div>
                </div>

                <form action="${pageContext.request.contextPath}/menus/<c:choose><c:when test="${!empty requestScope.content}">update?id=${requestScope.content.getId()}</c:when><c:otherwise>add</c:otherwise></c:choose>"
                      method="post" enctype="multipart/form-data" autocomplete="off">
                <div class="container bg-white rounded shadow-sm p-3">
                    <div class="row">
                        <div class="col-12 bg-dark text-white p-2 font-weight-bolder">Adatok</div>
                    </div>

                    <div class="row pt-3">
                        <div class="col-12 py-2 px-0">
                            <div class="form-row">
                                <div class="col-12 col-md-4 py-2 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="name">Menü neve</label>
                                            <input type="text" class="form-control" id="name" name="name" required="required"
                                                   value="<c:if test="${!empty requestScope.content}">${requestScope.content.getName()}</c:if>" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 py-2 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="duration">Elkészítési idő (perc)</label>
                                            <input type="number" class="form-control numeric" id="duration"
                                                   name="duration" required="required"
                                                   value="<c:if test="${!empty requestScope.content}">${requestScope.content.getDuration()}</c:if>" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 py-2 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="numberOfPersons">Adag (fő)</label>
                                            <input type="number" class="form-control numeric" id="numberOfPersons"
                                                   name="numberOfPersons" required="required"
                                                   value="<c:if test="${!empty requestScope.content}">${requestScope.content.getNumberOfPersons()}</c:if>" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container bg-white rounded shadow-sm mt-3 p-3">
                    <div class="row">
                        <div class="col-12 bg-dark text-white p-2 font-weight-bolder">Receptek</div>
                    </div>
                    <div class="row pt-3">
                        <div class="col-12 py-2 px-0">
                            <c:choose>
                                <c:when test="${!empty requestScope.content.getRecipes()}">
                                    <c:forEach items="${requestScope.content.getRecipes()}" var="item" varStatus="status">
                                    <div class="form-row row-item">
                                        <div class="form-group col-md-<c:out value="${status.index > 0 ? 11 : 12}" /> px-2">
                                            <label for="recipeId-${status.index}">Recept</label>
                                            <select class="form-control" name="recipeId"
                                                    id="recipeId-${status.index}" required="required">
                                                <c:if test="${empty requestScope.content}">
                                                    <option value="" disabled="disabled" selected="selected">Válasszon!</option>
                                                </c:if>
                                                <c:forEach items="${requestScope.recipes}" var="recipe">
                                                <option value="<c:out value="${recipe.getId()}"/>"
                                                    <c:if test="${item.getId() == recipe.getId()}">
                                                        selected="selected"</c:if>><c:out value="${recipe.getName()}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <c:if test="${status.index > 0}">
                                        <div class="form-group col-md-1 px-2">
                                            <button type="button" class="btn btn-operations-small btn-danger ml-1 button-delete-item" title="Törlés">
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                                </svg>
                                            </button>
                                        </div>
                                        </c:if>
                                    </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-row row-item">
                                        <div class="form-group col-md-12 px-2">
                                            <label for="recipeId-0">Recept</label>
                                            <select class="form-control" name="recipeId"
                                                    id="recipeId-0" required="required">
                                                <option value="" disabled="disabled" selected="selected">Válasszon!</option>
                                                <c:forEach items="${requestScope.recipes}" var="recipe">
                                                    <option value="<c:out value="${recipe.getId()}"/>"><c:out value="${recipe.getName()}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <div id="newItems"></div>
                            <div class="form-row px-2">
                                <button class="btn btn-sm btn-secondary" type="button" id="newItem"
                                        data-url="${pageContext.request.contextPath}/menus">+ Új recept</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4 p-0 px-md-2 form-buttons">
                    <div class="col-6 col-md-3 col-lg-2 p-0 pr-2 px-md-2">
                        <button type="submit" class="btn btn-primary form-main-button btn-block px-4">
                            <c:choose><c:when test="${!empty requestScope.content}">Módosítás</c:when><c:otherwise>Hozzáadás</c:otherwise> </c:choose></button>
                    </div>
                    <div class="col-6 col-md-3 col-lg-2 p-0 pl-2 px-md-2">
                        <a href="${pageContext.request.contextPath}/menus">
                            <button type="button" class="btn btn-secondary form-main-button btn-block px-4">Mégsem</button>
                        </a>
                    </div>
                </div>
                </form>
            </div>
        </section>
    </main>

<%@ include file="../common/footer.jsp" %>
<%@ include file="../../includes/scripts.jsp" %>
</body>
</html>
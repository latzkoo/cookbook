<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="../common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row mb-2">
                    <div class="col-6 p-0"><h3>Receptek</h3></div>
                    <div class="col-6 p-0">
                        <div class="button-add-new">
                            <a href="${pageContext.request.contextPath}/recipes/create">
                                <button class="btn btn-primary btn-sm">
                                    <svg width="20px" height="20px" viewBox="0 0 16 16" class="bi bi-plus mr-1" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                    </svg>Új recept</button></a>
                        </div>
                    </div>
                </div>

                <div class="container bg-white rounded shadow-sm px-0 py-3 mb-3">
                    <div class="row">
                        <div class="col-6 pl-3">
                            <div class="admin-list-items"><span class="font-weight-bolder">Összesen:</span> ${count} elem</div>
                        </div>
                        <div class="col-6 pt-1 pr-3">
                            <form class="form-inline ml-md-3 mt-md-0" action="" autocomplete="off">
                                <div class="input-group-sm ml-auto">
                                    <input class="form-control search-input" type="search" placeholder="Keresés"
                                           aria-label="Keresés" name="q" value="<c:if test="${!empty param.q}"><c:out value="${param.q}"/></c:if>" autocomplete="off" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${!empty param.success}">
                        <div class="row p-0">
                            <div class="col-12 p-0">
                                <div class="alert alert-success" role="alert">
                                    <div class="font-weight-bold">
                                    <c:choose><c:when test="${param.success.equals('add')}">A recept hozzáadása sikeres.</c:when><c:when test="${param.success.equals('modify')}">A recept módosítása sikeres.</c:when><c:when test="${param.success.equals('delete')}">A recept törlése sikeres.</c:when></c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${!empty param.error}">
                        <div class="row p-0">
                            <div class="col-12 p-0">
                                <div class="alert alert-danger" role="alert">
                                    <div class="font-weight-bold">
                                    <c:choose><c:when test="${param.error.equals('add')}">A recept hozzáadása sikertelen.</c:when><c:when test="${param.error.equals('modify')}">A recept módosítása sikertelen.</c:when><c:when test="${param.error.equals('delete')}">A recept nem törölhető, mert szerepel egy menüben.</c:when></c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>

                <div class="container bg-white rounded shadow-sm p-3">
                    <table class="table table-hover table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Megnevezés</th>
                            <th scope="col">Hozzávalók</th>
                            <th scope="col" class="fix150">Hozzáadva</th>
                            <th scope="col" class="fix150 text-right">Műveletek</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.recipes}" var="recipe">
                        <tr>
                            <td><a class="link-operation text-secondary"
                            href="${pageContext.request.contextPath}/recipes/edit?id=<c:out value="${recipe.getId()}"/>"><c:out value="${recipe.getName()}"/></a></td>
                            <td>${recipe.getMaterialItems()} db</td>
                            <td class="fix150">${recipe.getCreatedAt()}</td>
                            <td>
                                <div class="operations">
                                    <button type="button" class="btn btn-operations-small btn-success ml-1 button-cooking"
                                            data-name="${recipe.getName()}" data-id="${recipe.getId()}" title="Elkészítem">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive-fill" viewBox="0 0 16 16">
                                            <path d="M12.643 15C13.979 15 15 13.845 15 12.5V5H1v7.5C1 13.845 2.021 15 3.357 15h9.286zM5.5 7h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1zM.8 1a.8.8 0 0 0-.8.8V3a.8.8 0 0 0 .8.8h14.4A.8.8 0 0 0 16 3V1.8a.8.8 0 0 0-.8-.8H.8z"/>
                                        </svg>
                                    </button>
                                    <a href="${pageContext.request.contextPath}/recipes/edit?id=<c:out value="${recipe.getId()}"/>">
                                        <button type="button" class="btn btn-operations-small btn-secondary ml-1" title="Szerkesztés">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                                            </svg>
                                        </button>
                                    </a>
                                    <button type="button" class="btn btn-operations-small btn-danger ml-1 button-delete" title="Törlés"
                                            data-href="${pageContext.request.contextPath}/recipes/delete?id=<c:out value="${recipe.getId()}"/>">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                        </svg>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%@ include file="../common/pager.jsp" %>
            </div>
        </section>
    </main>

    <div class="modal" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>Törlés</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Biztos, hogy törli a receptet?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">Mégsem</button>
                    <a id="link-delete" href="${pageContext.request.contextPath}">
                        <button type="button" class="btn btn-sm btn-danger">Törlés</button></a>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="cookingModal" tabindex="-1" aria-labelledby="cookingModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="formCookingRecipe" action="${pageContext.request.contextPath}/cooking/recipe" method="post">
                    <input type="hidden" name="recipeId" value="" />
                    <div class="modal-header">
                        <h5 class="modal-title" id="cookingModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="numberOfPersons" class="col-form-label">Adag (fő):</label>
                            <input type="number" class="form-control numeric" name="numberOfPersons"
                                   id="numberOfPersons" value="4" min="1" />
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Mégsem</button>
                        <button type="button" class="btn btn-success" id="cookIt">Elkészítem</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file="../common/footer.jsp" %>
<%@ include file="../../includes/scripts.jsp" %>
</body>
</html>
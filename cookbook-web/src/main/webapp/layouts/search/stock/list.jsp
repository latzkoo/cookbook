<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="/layouts/common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row mb-2">
                    <div class="col-6 p-0"><h3>Alapanyag szerinti keresés</h3></div>
                    <div class="col-6 p-0">
                        <div class="button-add-new">
                            <a href="${pageContext.request.contextPath}/search/stock"><button class="btn btn-primary btn-sm">‹ Vissza a kereséshez</button></a>
                        </div>
                    </div>
                </div>

                <div class="container bg-white rounded shadow-sm px-0 py-3 mb-3">
                    <div class="row">
                        <div class="col-12 pl-3">
                            <div class="admin-list-items"><span class="font-weight-bolder">Összesen:</span> ${recipes.size() + menus.size()} elem</div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4 mb-2">
                    <div class="col-6 p-0"><h5>Receptek</h5></div>
                </div>
                <div class="container bg-white rounded shadow-sm p-3">
                    <table class="table table-hover table-sm">
                        <c:choose>
                        <c:when test="${!empty recipes}">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">Megnevezés</th>
                                <th scope="col">Hozzávalók</th>
                                <th scope="col">Elkészítési idő</th>
                                <th scope="col" class="fix150">Hozzáadva</th>
                                <th scope="col" class="fix150 text-right">Műveletek</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${recipes}" var="recipe">
                            <tr>
                                <td><a class="link-operation text-secondary"
                                href="${pageContext.request.contextPath}/recipes/edit?id=<c:out value="${recipe.getId()}"/>"><c:out value="${recipe.getName()}"/></a></td>
                                <td>${recipe.getMaterialItems()} db</td>
                                <td>${recipe.getDuration()} perc</td>
                                <td class="fix150">${recipe.getCreatedAt()}</td>
                                <td>
                                    <div class="operations">
                                        <button type="button" class="btn btn-operations-small btn-info ml-1 button-show" title="Megtekintés"
                                                data-href="${pageContext.request.contextPath}/recipes/show?id=<c:out value="${recipe.getId()}"/>">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-operations-small btn-success ml-1 button-prepare"
                                                data-name="${recipe.getName()}" data-id="${recipe.getId()}" title="Elkészítem">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive-fill" viewBox="0 0 16 16">
                                                <path d="M12.643 15C13.979 15 15 13.845 15 12.5V5H1v7.5C1 13.845 2.021 15 3.357 15h9.286zM5.5 7h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1zM.8 1a.8.8 0 0 0-.8.8V3a.8.8 0 0 0 .8.8h14.4A.8.8 0 0 0 16 3V1.8a.8.8 0 0 0-.8-.8H.8z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">Receptek</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="col pt-3">A megadott keresési feltételekkel nem található recept.</td>
                            </tr>
                            </tbody>
                        </c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </div>
        </section>
    </main>

    <div class="modal" id="prepareModal" tabindex="-1" aria-labelledby="prepareModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="formCookingRecipe" action="${pageContext.request.contextPath}/prepare/recipe" method="post">
                    <input type="hidden" name="recipeId" value="" />
                    <div class="modal-header">
                        <h5 class="modal-title" id="prepareModalLabel"></h5>
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
                        <button type="button" class="btn btn-success" id="prepareIt">Elkészítem</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<%@ include file="/layouts/common/footer.jsp" %>
<%@ include file="/includes/scripts.jsp" %>
</body>
</html>
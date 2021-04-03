<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="../common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row">
                    <div class="col-12 p-0"><h3>Bevásárlás › <span class="small">új bevásárlás</span></h3></div>
                </div>

                <c:choose>
                    <c:when test="${!empty param.success}">
                        <div class="row p-0">
                            <div class="col-12 p-0">
                                <div class="alert alert-success" role="alert">
                                    <div class="font-weight-bold">
                                        <c:choose>
                                            <c:when test="${param.success.equals('add')}">A bevásárlás hozzáadása sikeres.
                                                <a href="${pageContext.request.contextPath}/shopping/remove?materialId=${param.materialId}&stock=${param.stock}">Visszavonás</a>
                                            </c:when>
                                            <c:when test="${param.success.equals('remove')}">A bevásárlás visszavonása sikeres.</c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>

                <form action="${pageContext.request.contextPath}/shopping" method="post" autocomplete="off">
                <div class="container bg-white rounded shadow-sm p-3">
                    <div class="row">
                        <div class="col-12 bg-dark text-white p-2 font-weight-bolder">Adatok</div>
                    </div>

                    <div class="row pt-3">
                        <div class="col-12 py-2 px-0">
                            <div class="form-row">
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="shoppingMaterial">Alapanyag</label>
                                            <select class="form-control" name="materialId"
                                                    id="shoppingMaterial" required="required">
                                                <option value="" disabled="disabled" selected="selected">Válasszon!</option>
                                                <c:forEach items="${requestScope.materials}" var="material">
                                                    <option data-measure="${material.getMeasure().getName()}"
                                                            value="<c:out value="${material.getId()}"/>"
                                                            <c:if test="${item.getMaterial().getId() == material.getId()}">
                                                                selected="selected"</c:if>><c:out value="${material.getName()}" /></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group col-md-6 px-2">
                                    <label for="stock">Mennyiség</label>
                                    <input type="number" class="form-control numeric input-unit" id="stock"
                                           name="stock" required="required" value="" min="1" />
                                    <div class="measure">g</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4 p-0 px-md-2 form-buttons">
                    <div class="col-6 col-md-3 col-lg-2 p-0 pr-2 px-md-2">
                        <button type="submit" class="btn btn-primary form-main-button btn-block px-4">Hozzáadás</button>
                    </div>
                    <div class="col-6 col-md-3 col-lg-2 p-0 pl-2 px-md-2">
                        <button type="reset" class="btn btn-secondary form-main-button btn-block px-4">Mégsem</button>
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
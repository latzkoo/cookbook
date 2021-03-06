<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="measures" scope="request" type="java.util.List"/>
<jsp:useBean id="materials" scope="request" type="java.util.List"/>
<%@ include file="/includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="/layouts/common/header.jsp" %>

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
                                                <a href="${pageContext.request.contextPath}/shopping/remove?materialId=${param.materialId}&measureId=${param.measureId}&qty=${param.qty}">Visszavonás</a>
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
                            <div class="form-row row-item">
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="shoppingMaterial">Alapanyag</label>
                                            <select class="form-control" name="materialId"
                                                    id="shoppingMaterial" required="required">
                                                <option value="" disabled="disabled" selected="selected">Válasszon!</option>
                                                <c:forEach items="${materials}" var="material">
                                                    <option value="<c:out value="${material.getId()}"/>"
                                                            data-mc="<c:out value="${material.getMeasure().getCategoryId()}"/>"
                                                            data-mcofficial="<c:out value="${material.getOfficialMeasure().getCategoryId()}"/>"
                                                            data-mi="<c:out value="${material.getMeasure().getId()}"/>"
                                                            data-mcustomi="<c:out value="${material.getCustomMeasure().getId()}"/>">
                                                        <c:out value="${material.getName()}"/></option>
                                                </c:forEach>
                                            </select>
                                            <c:if test="${sessionScope.errors.containsKey('materialId')}">
                                                <span class="error">${sessionScope.errors.get('materialId')}</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group col-md-3 px-2">
                                    <label for="qty">Mennyiség</label>
                                    <input type="number" class="form-control numeric" id="qty"
                                           name="qty" required="required" value="" min="1" />
                                    <c:if test="${sessionScope.errors.containsKey('qty')}">
                                        <span class="error">${sessionScope.errors.get('qty')}</span>
                                    </c:if>
                                </div>

                                <div class="form-group col-md-3 px-2">
                                    <label for="measureId">Mennyiségi egység</label>
                                    <select class="form-control" name="measureId"
                                            id="measureId" required="required">
                                        <option value="" disabled="disabled" selected="selected">Válasszon!</option>
                                        <c:forEach items="${measures}" var="measure">
                                            <option value="<c:out value="${measure.getId()}"/>"
                                                    data-category="<c:out value="${measure.getCategoryId()}"/>"><c:out value="${measure.getName()}"/></option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${sessionScope.errors.containsKey('measureId')}">
                                        <span class="error">${sessionScope.errors.get('measureId')}</span>
                                    </c:if>
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

<%@ include file="/layouts/common/footer.jsp" %>
<%@ include file="/includes/scripts.jsp" %>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="count" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="materials" scope="request" type="java.util.List"/>
<%@ include file="/includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="/layouts/common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row mb-2">
                    <div class="col-12 p-0"><h3>Alapanyagok</h3></div>
                </div>

                <div class="container bg-white rounded shadow-sm px-0 py-3 mb-3">
                    <div class="row">
                        <div class="col-6 pl-3">
                            <div class="admin-list-items"><span class="font-weight-bolder">Összesen:</span> ${count} elem</div>
                        </div>
                        <div class="col-6 pt-1 pr-3">
                            <form name="filter" class="form-inline ml-md-3 mt-md-0" action="" autocomplete="off">
                                <div class="input-group-sm ml-auto">
                                    Szűrés: <select class="form-control select-stock autosend" name="status" id="status">
                                        <option value="">Minden alapanyag</option>
                                        <option value="outOfStock" <c:if test="${param.status.equals('outOfStock')}">selected="selected"</c:if>>
                                            Alacsony készlet</option>
                                    </select>
                                    <input class="form-control search-input" type="search" placeholder="Keresés"
                                           aria-label="Keresés" name="q" value="<c:if test="${!empty param.q}"><c:out value="${param.q}"/></c:if>" autocomplete="off" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="container bg-white rounded shadow-sm p-3">
                    <table class="table table-hover table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Megnevezés</th>
                            <th scope="col">Minimális készlet</th>
                            <th scope="col">Készlet</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${materials}" var="material">
                        <tr>
                            <td>${material.getName()}</td>
                            <td>${material.getMinStock()} ${material.getMeasure().getName()}</td>
                            <td><fmt:formatNumber type="number" pattern="#####.##"
                                    value="${material.getStock() / material.getMeasure().getMultiplier()}" />
                                    ${material.getMeasure().getName()}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%@ include file="/layouts/common/pager.jsp" %>
            </div>
        </section>
    </main>

<%@ include file="/layouts/common/footer.jsp" %>
<%@ include file="/includes/scripts.jsp" %>
</body>
</html>
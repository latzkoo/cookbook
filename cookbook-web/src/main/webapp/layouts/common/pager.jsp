<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${pager.getPages() > 1}">
<div class="container bg-transparent px-0 pt-3">
    <div class="row">
        <div class="col-12 pt-1 pl-0">
            <nav class="ml-auto">
                <ul class="pagination pagination-sm justify-content-center">
                    <li class="page-item <c:if test="${empty param.page || param.page == 1}">disabled</c:if>">
                    <a class="page-link" href="${requestScope['javax.servlet.forward.request_uri']}?page=<c:choose><c:when test="${!empty param.page}"><c:out value="${param.page - 1}"/></c:when><c:otherwise>1</c:otherwise></c:choose>">‹</a></li>
                    <c:forEach var="i" begin="1" end="${pager.getPages()}">
                        <li class="page-item <c:if test="${(empty param.page && i == 1) || (!empty param.page && param.page == i)}"> active</c:if>">
                        <a class="page-link" href="${requestScope['javax.servlet.forward.request_uri']}?page=${i}">${i}</a></li>
                    </c:forEach>
                    <li class="page-item <c:if test="${param.page == pager.getPages()}">disabled</c:if>">
                    <a class="page-link"
                       href="${requestScope['javax.servlet.forward.request_uri']}?page=<c:choose><c:when test="${!empty param.page}"><c:out value="${param.page + 1}"/></c:when><c:otherwise>${pager.getPages()}</c:otherwise></c:choose>">›</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</c:if>
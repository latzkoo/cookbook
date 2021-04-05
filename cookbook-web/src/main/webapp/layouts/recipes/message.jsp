<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="modal" id="cookingMessageModal" tabindex="-1" aria-labelledby="infoRecipeLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="infoRecipeLabel">${recipe.getName()} elkészítése</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:choose>
                    <c:when test="${!empty requestScope.outOfStockMaterials}">
                        <div class="row">
                            <div class="col col-12 px-0">
                                <div class="alert alert-danger" role="alert">
                                    <div class="font-weight-bold">A recept nem készíthető el,
                                        mert az alábbi alapanyagokból nincs elegendő mennyiség készleten:</div>
                                </div>
                            </div>
                        </div>
                        <table class="table table-sm">
                            <thead>
                                <tr class="row">
                                    <th scope="col" class="col-6">Alapanyag</th>
                                    <th scope="col" class="col-6">Szükséges mennyiség</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.outOfStockMaterials}" var="outOfStockMaterial">
                                <tr class="row">
                                    <td class="col-6">${outOfStockMaterial.getMaterial().getName()}</td>
                                    <td class="col-6"><fmt:formatNumber type="number" pattern="###.#" value="${outOfStockMaterial.getRequiredUnit()}" />
                                            ${outOfStockMaterial.getMeasure().getName()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Bezárás</button>
            </div>
        </div>
    </div>
</div>
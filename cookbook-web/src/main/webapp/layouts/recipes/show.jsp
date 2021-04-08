<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="modal" id="showRecipeModal" tabindex="-1" aria-labelledby="showRecipeLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="showRecipeLabel">${recipe.getName()}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <c:if test="${!empty recipe.getImage()}">
                    <div class="col col-12 col-md-6 px-0 pr-4">
                        <img src="data:image/jpeg;base64,${recipe.getImage()}" class="img-thumbnail" />
                    </div>
                    </c:if>
                    <div class="col col-12<c:if test="${!empty recipe.getImage()}"> col-md-6</c:if> px-0">
                        <h5>Hozzávalók ${recipe.getNumberOfPersons()} személyre</h5>
                        <div class="recipe-materials"><ul>
                            <c:forEach items="${recipe.getMaterials()}" var="item" varStatus="status">
                                <li><span class="font-weight-bolder">${item.getUnit()} ${item.getMeasure().getName()}</span> ${fn:toLowerCase(item.getMaterial().getName())}</li>
                            </c:forEach>
                        </ul></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12 pt-3 px-0">
                        <h5>Elkészítés</h5>
                        <div class="recipe-description">
                            <c:choose>
                            <c:when test="${!empty recipe.getDescription()}">${recipe.getDescription()}</c:when>
                            <c:otherwise>A recepthez nem tartozik leírás.</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Bezárás</button>
            </div>
        </div>
    </div>
</div>
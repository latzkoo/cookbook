<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="modal" id="showRecipeModal" tabindex="-1" aria-labelledby="showMenuLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="showMenuLabel">${menu.getName()} ${menu.getNumberOfPersons()} személyre</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col col-12 px-0">
                        <h5>Receptek</h5>
                        <div class="recipe-materials"><ul>
                            <c:forEach items="${menu.getRecipes()}" var="item" varStatus="status">
                                <li><span class="font-weight-bolder">${item.getName()}</span> (${item.getMaterials().size()} db hozzávaló)</li>
                            </c:forEach>
                        </ul></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-12 pt-3 px-0">
                        <h5>Elkészítési idő</h5>
                        <div>${menu.getDuration()} perc</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Bezárás</button>
            </div>
        </div>
    </div>
</div>
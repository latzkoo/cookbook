<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="form-row row-item">
    <div class="form-group col-md-6 px-2">
        <label for="materialId-">Alapanyag</label>
        <select class="form-control cikkID" name="materialId"
                id="materialId-" required="required">
            <option value="" disabled="disabled" selected="selected">Válasszon!</option>
            <c:forEach items="${requestScope.materials}" var="material">
                <option value="<c:out value="${material.getId()}"/>"><c:out value="${material.getName()}"/></option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group col-md-3 px-2">
        <label for="unit-">Mennyiség</label>
        <input type="number" class="form-control numeric" id="unit-"
               name="unit" required="required" value="${item.getUnit()}" min="1" />
    </div>
    <div class="form-group col-md-2 px-2">
        <label for="measureId-">Mennyiségi egység</label>
        <select class="form-control" name="measureId"
                id="measureId-" required="required">
            <option value="" disabled="disabled" selected="selected">Válasszon!</option>
            <c:forEach items="${requestScope.measures}" var="measure">
                <option value="<c:out value="${measure.getId()}"/>"><c:out value="${measure.getName()}"/></option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group col-md-1 px-2">
        <button type="button" class="btn btn-operations-small btn-danger ml-1 button-delete-item" title="Törlés">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
            </svg>
        </button>
    </div>
</div>
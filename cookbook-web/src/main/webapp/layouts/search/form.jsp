<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="/layouts/common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row">
                    <div class="col-12 p-0"><h3><a href="${pageContext.request.contextPath}/search">Keresés</a></h3></div>
                </div>

                <form action="${pageContext.request.contextPath}/search/results"
                      method="get" enctype="multipart/form-data" autocomplete="off">
                <div class="container bg-white rounded shadow-sm p-3">
                    <div class="row">
                        <div class="col-12 bg-dark text-white p-2 font-weight-bolder">Keresés</div>
                    </div>
                    <div class="row py-4 border-bottom">
                        <div class="col-12 px-0">
                            <span class="font-weight-bolder">Ezzel a keresővel a recepteket és menüket kereshet név, elkészítés nehézsége, kategória és elkészítési idő szerint.</span>
                        </div>
                    </div>
                    <div class="row pt-3">
                        <div class="col-12 py-2 px-0">
                            <div class="form-row">
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="name">Recept vagy menü neve</label>
                                            <input type="text" class="form-control" id="name" name="name" autofocus />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="levelId">Elkészítés nehézsége</label>
                                            <select class="form-control" name="levelId" id="levelId">
                                                <option value=""></option>
                                                <c:forEach items="${recipeLevels}" var="level">
                                                    <option value="${level.getId()}">${level.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12 py-2 px-0">
                            <div class="form-row">
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <div class="label">Kategória</div>
                                            <c:forEach items="${recipeCategories}" var="category" varStatus="status">
                                                <div class="custom-control categories custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" name="categoryId"
                                                           id="category-${status.index}" value="${category.getId()}">
                                                    <label class="custom-control-label" for="category-${status.index}">${category.getName()}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <div class="input-group">
                                                <label for="durationFrom">Elkészítési idő (perc)</label>
                                                <input type="number" id="durationFrom" placeholder="tól"
                                                       class="form-control input-rounded-left numeric" name="durationFrom" min="1" />
                                                <input type="number" placeholder="ig" class="form-control numeric" name="durationTo" min="1" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4 p-0 px-md-2 form-buttons">
                    <div class="col-6 col-md-3 col-lg-2 p-0 pr-2 px-md-2">
                        <button type="submit" class="btn btn-primary form-main-button btn-block px-4">Keresés</button>
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
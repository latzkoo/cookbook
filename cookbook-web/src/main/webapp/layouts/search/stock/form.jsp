<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/head.jsp" %>

<body class="d-flex flex-column h-100 bg-light">
<%@ include file="/layouts/common/header.jsp" %>

    <main role="main" class="flex-shrink-0">
        <section class="content my-5">
            <div class="container m-auto">
                <div class="row">
                    <div class="col-12 p-0"><h3><a href="${pageContext.request.contextPath}/search/stock">Alapanyag szerinti keresés</a></h3></div>
                </div>

                <form action="${pageContext.request.contextPath}/search/stock/results"
                      method="get" enctype="multipart/form-data" autocomplete="off">
                <div class="container bg-white rounded shadow-sm p-3">
                    <div class="row">
                        <div class="col-12 bg-dark text-white p-2 font-weight-bolder">Keresés</div>
                    </div>
                    <div class="row py-4 border-bottom">
                        <div class="col-12 px-0">
                            <span class="font-weight-bolder">Ennek a keresőnek a segítségével megnézheti, hogy mely receptek elkészítéséhez áll elegendő alapanyag rendelkezésre.</span>
                        </div>
                    </div>
                    <div class="row pt-3">
                        <div class="col-12 py-2 px-0">
                            <div class="form-row">
                                <div class="col-12 col-md-3 px-md-2">
                                    <div class="form-row">
                                        <div class="form-group col-12">
                                            <label for="numberOfPersons">Hány főre szeretne főzni?</label>
                                            <input type="number" class="form-control numeric" id="numberOfPersons"
                                                   name="numberOfPersons" min="1" required="required" autofocus />
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
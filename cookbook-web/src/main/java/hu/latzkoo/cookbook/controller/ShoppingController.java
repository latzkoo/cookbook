package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping/", "/shopping"})
public class ShoppingController extends HttpServlet {

    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("materials", materialDAO.findAll(false));
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/shopping/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("materialId").isEmpty() && !request.getParameter("qty").isEmpty() &&
                !request.getParameter("measureId").isEmpty()) {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            int measureId = Integer.parseInt(request.getParameter("measureId"));
            int qty = Integer.parseInt(request.getParameter("qty"));

            Material material = materialDAO.findById(materialId);

            if (materialId > 0 && qty > 0 && measureId > 0) {
                Measure measure = measureDAO.findById(material.getOfficialMeasureId() > 0 &&
                        material.getMeasureId() == measureId ? material.getOfficialMeasureId() : measureId);
                int unitQty = qty * measure.getMultiplier();

                if (material.getOfficialMeasureId() > 0) {
                    unitQty *= material.getOfficialMeasureUnit();
                }

                materialDAO.updateStock("increase", materialId, unitQty);

                response.sendRedirect(request.getContextPath() + "/shopping?success=add&materialId=" +
                        request.getParameter("materialId") + "&measureId=" + request.getParameter("measureId") + "&qty=" +
                        request.getParameter("qty"));
            }
        }
    }
}

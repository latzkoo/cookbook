package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import hu.latzkoo.cookbook.model.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping")
public class ShoppingController extends HttpServlet {

    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("error") == null) {
            request.getSession().removeAttribute("errors");
        }

        request.setAttribute("materials", materialDAO.findAll(false));
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/shopping/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validation validation = new Validation(request, new String[]{"materialId", "qty", "measureId"});

        if (validation.getErrors().size() > 0) {
            request.getSession().setAttribute("errors", validation.getErrors());
            String page = request.getParameter("id") != null ? "?id=" + request.getParameter("id") + "&error" : "?error";
            response.sendRedirect(request.getContextPath() + "/shopping" + page);
        }
        else {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            int measureId = Integer.parseInt(request.getParameter("measureId"));
            int qty = Integer.parseInt(request.getParameter("qty"));

            Material material = materialDAO.findById(materialId);

            if (materialId > 0 && qty > 0 && measureId > 0) {
                Measure measure = measureDAO.findById(material.getCustomMeasureId() > 0 &&
                        material.getCustomMeasureId() == measureId ? material.getOfficialMeasureId() : measureId);

                int unitQty = qty * measure.getMultiplier();

                if (material.getCustomMeasureId() == measureId) {
                    unitQty = qty * measure.getMultiplier() * material.getOfficialMeasureUnit();
                }

                materialDAO.updateStock("increase", materialId, unitQty);

                response.sendRedirect(request.getContextPath() + "/shopping?success=add&materialId=" +
                        request.getParameter("materialId") + "&measureId=" + request.getParameter("measureId") + "&qty=" +
                        request.getParameter("qty"));
            }
        }
    }
}

package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import hu.latzkoo.cookbook.model.Recipe;
import hu.latzkoo.cookbook.model.RecipeMaterial;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(urlPatterns = {"/shopping/", "/shopping"})
public class ShoppingController extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();
        request.setAttribute("materials", materialDAO.findAll(false));

        MeasureDAO measureDAO = new MeasureDAOImpl();
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/shopping/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();

        materialDAO.updateStock(Integer.parseInt(request.getParameter("materialId")),
                Integer.parseInt(request.getParameter("stock")));

        response.sendRedirect(request.getContextPath() + "/shopping?success=add");
    }
}

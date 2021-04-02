package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/item")
public class RecipeItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();
        request.setAttribute("materials", materialDAO.findAll(false));

        MeasureDAO measureDAO = new MeasureDAOImpl();
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/recipes/item.jsp").forward(request, response);
    }
}

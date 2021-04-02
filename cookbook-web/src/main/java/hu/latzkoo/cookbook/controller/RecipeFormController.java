package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/recipes/create", "/recipes/edit"})
public class RecipeFormController extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Edit
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));

            RecipeDAO recipeDAO = new RecipeDAOImpl();
            Recipe content = recipeDAO.findById(id);
            request.setAttribute("content", content);
        }

        RecipeCategoryDAO recipeCategoryDAO = new RecipeCategoryDAOImpl();
        request.setAttribute("recipeCategories", recipeCategoryDAO.findAll());

        RecipeLevelDAO recipeLevelDAO = new RecipeLevelDAOImpl();
        request.setAttribute("recipeLevels", recipeLevelDAO.findAll());

        MaterialDAO materialDAO = new MaterialDAOImpl();
        request.setAttribute("materials", materialDAO.findAll(false));

        MeasureDAO measureDAO = new MeasureDAOImpl();
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/recipes/form.jsp").forward(request, response);
    }

}

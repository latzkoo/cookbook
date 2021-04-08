package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/search/", "/search"})
public class SearchFormController extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeCategoryDAO recipeCategoryDAO = new RecipeCategoryDAOImpl();
        request.setAttribute("recipeCategories", recipeCategoryDAO.findAll());

        RecipeLevelDAO recipeLevelDAO = new RecipeLevelDAOImpl();
        request.setAttribute("recipeLevels", recipeLevelDAO.findAll());

        request.getRequestDispatcher("/layouts/search/form.jsp").forward(request, response);
    }

}

package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.RecipeDAO;
import hu.latzkoo.cookbook.dao.RecipeDAOImpl;
import hu.latzkoo.cookbook.model.Pager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/recipes", ""})
public class RecipeListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAOImpl();

        int count = recipeDAO.count(request.getParameter("q"));
        request.setAttribute("count", count);

        Pager pager = new Pager((request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) : 0), count);
        request.setAttribute("pager", pager);
        request.setAttribute("recipes", recipeDAO.findAll(request.getParameter("q"), pager));

        request.getRequestDispatcher("recipe.jsp").forward(request, response);
    }
}

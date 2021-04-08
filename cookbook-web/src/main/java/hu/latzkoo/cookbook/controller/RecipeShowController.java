package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/show")
public class RecipeShowController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        request.setAttribute("recipe", recipeDAO.findById(Integer.parseInt(request.getParameter("id"))));

        request.getRequestDispatcher("/layouts/recipes/show.jsp").forward(request, response);
    }
}

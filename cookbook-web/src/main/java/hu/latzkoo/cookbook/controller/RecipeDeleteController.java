package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.RecipeDAO;
import hu.latzkoo.cookbook.dao.RecipeDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/recipes/delete"})
public class RecipeDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        recipeDAO.delete(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(request.getContextPath() + "/recipes?success=delete");
    }
}

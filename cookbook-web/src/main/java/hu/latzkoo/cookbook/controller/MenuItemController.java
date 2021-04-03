package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menus/item")
public class MenuItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        request.setAttribute("recipes", recipeDAO.findAll());

        request.getRequestDispatcher("/layouts/menus/item.jsp").forward(request, response);
    }
}

package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/menus/create", "/menus/edit"})
public class MenuFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Edit
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu content = menuDAO.findById(id);
            request.setAttribute("content", content);
        }

        RecipeDAO recipeDAO = new RecipeDAOImpl();
        request.setAttribute("recipes", recipeDAO.findAll());

        request.getRequestDispatcher("/layouts/menus/form.jsp").forward(request, response);
    }

}

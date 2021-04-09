package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.RecipeDAO;
import hu.latzkoo.cookbook.dao.RecipeDAOImpl;
import hu.latzkoo.cookbook.service.RecipeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search/stock/results")
public class SearchStockListController extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAOImpl();
    private final RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("numberOfPersons") != null && !request.getParameter("numberOfPersons").isEmpty()) {
            int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));

            request.setAttribute("recipes", recipeService.canPrepare(recipeDAO.findAll(), numberOfPersons));
            request.getRequestDispatcher("/layouts/search/stock/list.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/layouts/search/stock?error=fields");
        }
    }
}

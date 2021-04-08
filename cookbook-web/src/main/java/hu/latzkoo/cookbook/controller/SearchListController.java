package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/search/results/", "/search/results"})
public class SearchListController extends HttpServlet {

    private final RecipeDAO recipeDAO = new RecipeDAOImpl();
    private final MenuDAO menuDAO = new MenuDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        String[] categoryIds = new String[0];
        if (request.getParameter("categoryId") != null && !request.getParameter("categoryId").isEmpty()) {
            categoryIds = request.getParameterValues("categoryId");
        }

        int levelId = 0;
        if (request.getParameter("levelId") != null && !request.getParameter("levelId").isEmpty()) {
            levelId = Integer.parseInt(request.getParameter("levelId"));
        }

        int durationFrom = 0;
        if (request.getParameter("durationFrom") != null && !request.getParameter("durationFrom").isEmpty()) {
            durationFrom = Integer.parseInt(request.getParameter("durationFrom"));
        }

        int durationTo = 0;
        if (request.getParameter("durationTo") != null && !request.getParameter("durationTo").isEmpty()) {
            durationTo = Integer.parseInt(request.getParameter("durationTo"));
        }

        if (request.getParameter("type") == null || request.getParameter("type") != null &&
                !request.getParameter("type").equals("menus")) {
            request.setAttribute("recipes", recipeDAO.search(name, categoryIds, levelId, durationFrom, durationTo));
        }

        if (request.getParameter("type") == null || request.getParameter("type") != null &&
                !request.getParameter("type").equals("recipes")) {
            request.setAttribute("menus", menuDAO.search(name, durationFrom, durationTo));
        }

        request.getRequestDispatcher("/layouts/search/list.jsp").forward(request, response);
    }
}

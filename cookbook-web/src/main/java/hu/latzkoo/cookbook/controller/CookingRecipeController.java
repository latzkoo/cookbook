package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Recipe;
import hu.latzkoo.cookbook.model.RecipeMaterial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/cooking/recipe"})
public class CookingRecipeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("recipeId").isEmpty() && !request.getParameter("numberOfPersons").isEmpty()) {
            RecipeDAO recipeDAO = new RecipeDAOImpl();

            Recipe recipe = recipeDAO.findById(Integer.parseInt(request.getParameter("recipeId")));
            int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));

            List<RecipeMaterial> outOfStockMaterials = new ArrayList<>();
            for (RecipeMaterial recipeMaterial : recipe.getMaterials()) {
                double unitPerPerson = (double) recipeMaterial.getUnit() / recipe.getNumberOfPersons();
                double unit = unitPerPerson * numberOfPersons;

                if (unit > recipeMaterial.getMaterial().getStock()) {
                    recipeMaterial.setRequiredUnit(unit);
                    outOfStockMaterials.add(recipeMaterial);
                }
            }

            request.setAttribute("recipe", recipe);

            if (!outOfStockMaterials.isEmpty()) {
                request.setAttribute("outOfStockMaterials", outOfStockMaterials);
            }

            request.getRequestDispatcher("/layouts/recipes/message.jsp").forward(request, response);
        }
    }
}

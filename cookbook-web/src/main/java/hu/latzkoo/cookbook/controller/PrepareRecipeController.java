package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Recipe;
import hu.latzkoo.cookbook.model.RecipeMaterial;
import hu.latzkoo.cookbook.service.RecipeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/cooking/recipe"})
public class PrepareRecipeController extends HttpServlet {

    private final RecipeService recipeService = new RecipeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("recipeId").isEmpty() && !request.getParameter("numberOfPersons").isEmpty()) {
            RecipeDAO recipeDAO = new RecipeDAOImpl();

            Recipe recipe = recipeDAO.findById(Integer.parseInt(request.getParameter("recipeId")));
            int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));

            recipeService.setRecipe(recipe);
            List<RecipeMaterial> outOfStockMaterials = recipeService.canPrepare(numberOfPersons);

            request.setAttribute("recipe", recipe);

            // Out of stock
            if (!outOfStockMaterials.isEmpty()) {
                request.setAttribute("outOfStockMaterials", outOfStockMaterials);
            }
            // Prepare the food
            else {
//                recipeService.prepare();
            }

            request.getRequestDispatcher("/layouts/recipes/message.jsp").forward(request, response);
        }
    }

}

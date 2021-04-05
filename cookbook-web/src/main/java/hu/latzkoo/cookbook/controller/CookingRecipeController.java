package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.Measure;
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

    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("recipeId").isEmpty() && !request.getParameter("numberOfPersons").isEmpty()) {
            RecipeDAO recipeDAO = new RecipeDAOImpl();

            Recipe recipe = recipeDAO.findById(Integer.parseInt(request.getParameter("recipeId")));
            int numberOfPersons = Integer.parseInt(request.getParameter("numberOfPersons"));

            List<RecipeMaterial> outOfStockMaterials = new ArrayList<>();
            for (RecipeMaterial recipeMaterial : recipe.getMaterials()) {
                Measure measure;

                if (recipeMaterial.getMaterial().getCustomMeasureId() > 0 &&
                        recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
                    measure = measureDAO.findById(recipeMaterial.getMaterial().getOfficialMeasureId());
                }
                else {
                    measure = recipeMaterial.getMeasure();
                }

                int unitQty = recipeMaterial.getUnit() * measure.getMultiplier();

                if (recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
                    unitQty *= recipeMaterial.getMaterial().getOfficialMeasureUnit();
                }

                double unitPerPerson = (double) unitQty / recipe.getNumberOfPersons();
                double unit = unitPerPerson * numberOfPersons;

                if (unit > recipeMaterial.getMaterial().getStock()) {
                    unit = unit / measure.getMultiplier();

                    if (recipeMaterial.getMaterial().getCustomMeasureId() > 0 &&
                            recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
                        unit /= recipeMaterial.getMaterial().getOfficialMeasureUnit();
                    }

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

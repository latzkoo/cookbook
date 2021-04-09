package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.RecipeDAO;
import hu.latzkoo.cookbook.dao.RecipeDAOImpl;
import hu.latzkoo.cookbook.dao.RecipeMaterialDAO;
import hu.latzkoo.cookbook.dao.RecipeMaterialDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import hu.latzkoo.cookbook.model.Recipe;
import hu.latzkoo.cookbook.model.RecipeMaterial;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(urlPatterns = {"/recipes/add", "/recipes/update"})
@MultipartConfig(fileSizeThreshold = 1048576 * 5, maxFileSize = 1048576 * 5, maxRequestSize = 1048576 * 25)
public class RecipeSaveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        Recipe recipe = new Recipe();
        String event = "add";

        if (request.getParameter("id") != null) {
            recipe.setId(Integer.parseInt(request.getParameter("id")));
            event = "modify";
        }

        // Fields
        recipe.setName(request.getParameter("name"));
        recipe.setCustomName(request.getParameter("customName"));
        recipe.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        recipe.setDescription(request.getParameter("description"));
        recipe.setLevelId(Integer.parseInt(request.getParameter("levelId")));
        recipe.setDuration(Integer.parseInt(request.getParameter("duration")));
        recipe.setNumberOfPersons(Integer.parseInt(request.getParameter("numberOfPersons")));
        recipe.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));

        // Image
        if (request.getPart("image").getSize() > 0) {
            byte[] file = IOUtils.toByteArray(request.getPart("image").getInputStream());
            String image = Base64.getEncoder().encodeToString(file);
            recipe.setImage(image);
        }
        else if (request.getParameter("deleteImage") != null) {
            recipe.setImage("");
        }

        recipe = recipeDAO.save(recipe);

        String[] materialIds = request.getParameterValues("materialId");
        String[] measureIds = request.getParameterValues("measureId");
        String[] units = request.getParameterValues("unit");

        if (recipe.getId() > 0 && materialIds.length > 0) {
            List<RecipeMaterial> recipeMaterials = new ArrayList<>();

            for (int i = 0; i < materialIds.length; i++) {
                RecipeMaterial recipeMaterial = new RecipeMaterial();
                recipeMaterial.setRecipeId(recipe.getId());
                recipeMaterial.setMaterial(new Material(Integer.parseInt(materialIds[i])));
                recipeMaterial.setMeasure(new Measure(Integer.parseInt(measureIds[i])));
                recipeMaterial.setUnit(Integer.parseInt(units[i]));

                recipeMaterials.add(recipeMaterial);
            }

            RecipeMaterialDAO recipeMaterialDAO = new RecipeMaterialDAOImpl();
            recipeMaterialDAO.deleteByRecipeId(recipe.getId());
            recipeMaterialDAO.insert(recipeMaterials);
        }

        response.sendRedirect(request.getContextPath() + "/recipes?success=" + event);
    }

}

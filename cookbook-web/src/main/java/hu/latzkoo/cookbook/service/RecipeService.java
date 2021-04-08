package hu.latzkoo.cookbook.service;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.dao.MeasureDAO;
import hu.latzkoo.cookbook.dao.MeasureDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import hu.latzkoo.cookbook.model.Recipe;
import hu.latzkoo.cookbook.model.RecipeMaterial;

import java.util.ArrayList;
import java.util.List;

public class RecipeService {

    private Recipe recipe;
    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    public RecipeService() {
    }

    public RecipeService(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public RecipeService setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    /**
     * Check the recipe is can be prepared.
     *
     * @param numberOfPersons the number of persons to prepare the recipe.
     * @return the list of materials that are missing to prepare the recipe.
     */
    public List<RecipeMaterial> canPrepare(int numberOfPersons) {
        List<RecipeMaterial> outOfStockMaterials = new ArrayList<>();

        for (RecipeMaterial recipeMaterial : recipe.getMaterials()) {
            Measure measure = getOfficialMeasure(recipeMaterial);

            double unitPerPerson = getUnitPerPerson(recipeMaterial, measure);
            double unitInDefaultMeasure = unitPerPerson * numberOfPersons;

            if (unitInDefaultMeasure > recipeMaterial.getMaterial().getStock()) {
                double unitInRecipeMeasure = unitInDefaultMeasure / measure.getMultiplier();

                if (recipeMaterial.getMaterial().getCustomMeasureId() > 0 &&
                        recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
                    unitInRecipeMeasure /= recipeMaterial.getMaterial().getOfficialMeasureUnit();
                }

                recipeMaterial.setRequiredUnit(unitInRecipeMeasure);
                outOfStockMaterials.add(recipeMaterial);
            }
        }

        return outOfStockMaterials;
    }

    /**
     * Prepare a recipe.
     *
     * @param numberOfPersons the number of persons to prepare the recipe.
     * @return the list of materials that are below the minimum stock.
     */
    public List<RecipeMaterial> prepare(int numberOfPersons) {
        List<RecipeMaterial> belowMinimumStockMaterials = new ArrayList<>();

        for (RecipeMaterial recipeMaterial : recipe.getMaterials()) {
            Measure measure = getOfficialMeasure(recipeMaterial);

            double unitPerPerson = getUnitPerPerson(recipeMaterial, measure);
            double unitInDefaultMeasure = unitPerPerson * numberOfPersons;

            Material material = materialDAO.updateStock("decrease", recipeMaterial.getMaterial().getId(), (int) unitInDefaultMeasure);

            material.setStock(material.getStock() / measure.getMultiplier());

            if (material.getStock() < material.getMinStock()) {
                recipeMaterial.setMaterial(material);
                belowMinimumStockMaterials.add(recipeMaterial);
            }
        }

        return belowMinimumStockMaterials;
    }

    /**
     * Determines the material need per person in official measure.
     *
     * @param recipeMaterial the material that is part of the recipe.
     * @param officialMeasure the official measure that is part of the recipe.
     * @return the material need per person in official measure.
     */
    private double getUnitPerPerson(RecipeMaterial recipeMaterial, Measure officialMeasure) {
        int unitQty = recipeMaterial.getUnit() * officialMeasure.getMultiplier();

        if (recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
            unitQty *= recipeMaterial.getMaterial().getOfficialMeasureUnit();
        }

        return (double) unitQty / recipe.getNumberOfPersons();
    }

    /**
     * Determines the official measure of the material.
     *
     * @param recipeMaterial the material that is part of the recipe.
     * @return the official measure of the material.
     */
    private Measure getOfficialMeasure(RecipeMaterial recipeMaterial) {
        Measure measure;

        if (recipeMaterial.getMaterial().getCustomMeasureId() > 0 &&
                recipeMaterial.getMaterial().getCustomMeasureId() == recipeMaterial.getMeasure().getId()) {
            measure = measureDAO.findById(recipeMaterial.getMaterial().getOfficialMeasureId());
        }
        else {
            measure = recipeMaterial.getMeasure();
        }

        return measure;
    }

}

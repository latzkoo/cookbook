package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.RecipeMaterial;

import java.util.List;

public interface RecipeMaterialDAO {

    List<RecipeMaterial> findAll(int id);
    void insert(List<RecipeMaterial> recipeMaterials);
    void deleteByRecipeId(int recipeId);

}

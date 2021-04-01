package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.RecipeCategory;

import java.util.List;

public interface RecipeCategoryDAO {

    List<RecipeCategory> findAll();
    RecipeCategory findById(int id);

}

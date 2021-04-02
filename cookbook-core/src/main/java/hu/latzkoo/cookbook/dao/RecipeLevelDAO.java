package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.RecipeLevel;

import java.util.List;

public interface RecipeLevelDAO {

    List<RecipeLevel> findAll();
    RecipeLevel findById(int id);

}

package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Pager;
import hu.latzkoo.cookbook.model.Recipe;

import java.util.List;

public interface RecipeDAO {

    int count(String q);
    List<Recipe> findAll();
    List<Recipe> findAll(String q, Pager pager);
    List<Recipe> search(String name, String[] categoryIds, int levelId, int durationFrom, int durationTo);
    Recipe findById(int id);
    Recipe save(Recipe recipe);
    boolean delete(Recipe recipe);
    boolean delete(int id);

}

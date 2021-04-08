package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Pager;
import hu.latzkoo.cookbook.model.Menu;

import java.util.List;

public interface MenuDAO {

    int count(String q);
    List<Menu> findAll(String q, Pager pager);
    List<Menu> search(String name, int durationFrom, int durationTo);
    Menu findById(int id);
    Menu save(Menu menu);
    void delete(Menu menu);
    void delete(int id);
    void deleteRecipes(int menuId);
    void insertRecipes(int menuId, int[] recipeIds);

}

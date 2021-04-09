package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Pager;

import java.util.List;

public interface MaterialDAO {

    int count(boolean outOfStock, String q);
    List<Material> findAll(boolean outOfStock);
    List<Material> findAll(boolean outOfStock, String q, Pager pager);
    Material findById(int id);
    Material save(Material material);
    Material updateStock(String event, int id, int stock);
    void delete(Material material);

}

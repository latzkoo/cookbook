package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Material;

import java.util.List;

public interface MaterialDAO {

    List<Material> findAll(boolean outOfStock);
    Material findById(int id);
    Material save(Material material);
    void updateStock(int id, int stock);
    void delete(Material material);

}

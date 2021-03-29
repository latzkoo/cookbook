package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Material;

import java.util.List;

public interface MaterialDAO {

    List<Material> findAll();
    Material save(Material material);
    void delete(Material material);

}

package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Measure;

import java.util.List;

public interface MeasureDAO {

    List<Measure> findAll();
    Measure findById(int id);
    Measure save(Measure measure);
    void delete(Measure measure);

}

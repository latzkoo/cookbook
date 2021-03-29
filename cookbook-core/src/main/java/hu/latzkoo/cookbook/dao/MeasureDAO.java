package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.model.Measure;

import java.util.List;

public interface MeasureDAO {

    List<Measure> get();
    Measure getById(int id);
    Measure save(Measure measure);
    void delete(Measure measure);

}

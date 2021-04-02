package hu.latzkoo.cookbook.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RecipeMaterial {

    private IntegerProperty recipeId = new SimpleIntegerProperty(this, "recipeId");
    private ObjectProperty<Material> material = new SimpleObjectProperty<>(this, "material");
    private ObjectProperty<Measure> measure = new SimpleObjectProperty<>(this, "measure");
    private IntegerProperty unit = new SimpleIntegerProperty(this, "unit");

    public int getRecipeId() {
        return recipeId.get();
    }

    public IntegerProperty recipeIdProperty() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId.set(recipeId);
    }

    public Material getMaterial() {
        return material.get();
    }

    public ObjectProperty<Material> materialProperty() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material.set(material);
    }

    public Measure getMeasure() {
        return measure.get();
    }

    public ObjectProperty<Measure> measureProperty() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure.set(measure);
    }

    public int getUnit() {
        return unit.get();
    }

    public IntegerProperty unitProperty() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit.set(unit);
    }
}

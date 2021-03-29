package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;

public class Material {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty measureId = new SimpleIntegerProperty(this, "measureId");
    private StringProperty name = new SimpleStringProperty(this, "name");
//    private ObjectProperty<Measure> measure = new SimpleObjectProperty<>(this, "measure");
    private IntegerProperty minStock = new SimpleIntegerProperty(this, "minStock");
    private IntegerProperty officialMeasureId = new SimpleIntegerProperty(this, "officialMeasureId");
    private IntegerProperty officialMeasureUnit = new SimpleIntegerProperty(this, "officialMeasureUnit");
//    private ObjectProperty<Measure> officialMeasure = new SimpleObjectProperty<>(this, "officialMeasure");
//    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this, "date");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getMeasureId() {
        return measureId.get();
    }

    public IntegerProperty measureIdProperty() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId.set(measureId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getMinStock() {
        return minStock.get();
    }

    public IntegerProperty minStockProperty() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock.set(minStock);
    }

    public int getOfficialMeasureId() {
        return officialMeasureId.get();
    }

    public IntegerProperty officialMeasureIdProperty() {
        return officialMeasureId;
    }

    public void setOfficialMeasureId(int officialMeasureId) {
        this.officialMeasureId.set(officialMeasureId);
    }

    public int getOfficialMeasureUnit() {
        return officialMeasureUnit.get();
    }

    public IntegerProperty officialMeasureUnitProperty() {
        return officialMeasureUnit;
    }

    public void setOfficialMeasureUnit(int officialMeasureUnit) {
        this.officialMeasureUnit.set(officialMeasureUnit);
    }
}

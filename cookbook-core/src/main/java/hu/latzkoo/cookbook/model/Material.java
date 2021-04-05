package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;

public class Material {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty measureId = new SimpleIntegerProperty(this, "measureId");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final ObjectProperty<Measure> measure = new SimpleObjectProperty<>(this, "measure");
    private final IntegerProperty officialMeasureUnit = new SimpleIntegerProperty(this, "officialMeasureUnit");
    private final IntegerProperty officialMeasureId = new SimpleIntegerProperty(this, "officialMeasureId");
    private final IntegerProperty customMeasureId = new SimpleIntegerProperty(this, "customMeasureId");
    private final IntegerProperty minStock = new SimpleIntegerProperty(this, "minStock");
    private final IntegerProperty stock = new SimpleIntegerProperty(this, "stock");
    private final ObjectProperty<Measure> customMeasure = new SimpleObjectProperty<>(this, "customMeasure");
    private final ObjectProperty<Measure> officialMeasure = new SimpleObjectProperty<>(this, "officialMeasure");
    private final DoubleProperty measureUnit = new SimpleDoubleProperty(this, "measureUnit");

    public Material() {
    }

    public Material(int id) {
        setId(id);
    }

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

    public Measure getMeasure() {
        return measure.get();
    }

    public ObjectProperty<Measure> measureProperty() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure.set(measure);
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

    public int getOfficialMeasureId() {
        return officialMeasureId.get();
    }

    public IntegerProperty officialMeasureIdProperty() {
        return officialMeasureId;
    }

    public void setOfficialMeasureId(int officialMeasureId) {
        this.officialMeasureId.set(officialMeasureId);
    }

    public int getCustomMeasureId() {
        return customMeasureId.get();
    }

    public IntegerProperty customMeasureIdProperty() {
        return customMeasureId;
    }

    public void setCustomMeasureId(int customMeasureId) {
        this.customMeasureId.set(customMeasureId);
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

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public Measure getCustomMeasure() {
        return customMeasure.get();
    }

    public ObjectProperty<Measure> customMeasureProperty() {
        return customMeasure;
    }

    public void setCustomMeasure(Measure customMeasure) {
        this.customMeasure.set(customMeasure);
    }

    public Measure getOfficialMeasure() {
        return officialMeasure.get();
    }

    public ObjectProperty<Measure> officialMeasureProperty() {
        return officialMeasure;
    }

    public void setOfficialMeasure(Measure officialMeasure) {
        this.officialMeasure.set(officialMeasure);
    }

    public double getMeasureUnit() {
        return measureUnit.get();
    }

    public DoubleProperty measureUnitProperty() {
        return measureUnit;
    }

    public void setMeasureUnit(double measureUnit) {
        this.measureUnit.set(measureUnit);
    }
}

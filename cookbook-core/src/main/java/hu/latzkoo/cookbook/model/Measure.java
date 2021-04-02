package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;

public class Measure {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private IntegerProperty measureType = new SimpleIntegerProperty(this, "measureType");

    public Measure() {
    }

    public Measure(int id) {
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getMeasureType() {
        return measureType.get();
    }

    public IntegerProperty measureTypeProperty() {
        return measureType;
    }

    public void setMeasureType(int measureType) {
        this.measureType.set(measureType);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}

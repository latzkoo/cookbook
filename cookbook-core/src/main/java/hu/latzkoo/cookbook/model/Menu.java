package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty createdAt = new SimpleStringProperty(this, "createdAt");
    private IntegerProperty recipeItems = new SimpleIntegerProperty(this, "recipeItems");
    private IntegerProperty duration = new SimpleIntegerProperty(this, "duration");
    private IntegerProperty numberOfPersons = new SimpleIntegerProperty(this, "numberOfPersons");
    private List<Recipe> recipes = new ArrayList<>();

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

    public String getCreatedAt() {
        return createdAt.get();
    }

    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt.set(createdAt);
    }

    public int getRecipeItems() {
        return recipeItems.get();
    }

    public IntegerProperty recipeItemsProperty() {
        return recipeItems;
    }

    public void setRecipeItems(int recipeItems) {
        this.recipeItems.set(recipeItems);
    }

    public int getDuration() {
        return duration.get();
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public int getNumberOfPersons() {
        return numberOfPersons.get();
    }

    public IntegerProperty numberOfPersonsProperty() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons.set(numberOfPersons);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

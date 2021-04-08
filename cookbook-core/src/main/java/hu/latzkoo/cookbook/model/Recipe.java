package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty categoryId = new SimpleIntegerProperty(this, "categoryId");
    private final ObjectProperty<RecipeCategory> recipeCategory = new SimpleObjectProperty<>(this, "recipeCategory");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty customName = new SimpleStringProperty(this, "customName");
    private final StringProperty description = new SimpleStringProperty(this, "description");
    private final SimpleIntegerProperty levelId = new SimpleIntegerProperty(this, "levelId");
    private final ObjectProperty<RecipeLevel> recipeLevel = new SimpleObjectProperty<>(this, "recipeLevel");
    private final SimpleIntegerProperty duration = new SimpleIntegerProperty(this, "duration");
    private final SimpleIntegerProperty numberOfPersons = new SimpleIntegerProperty(this, "numberOfPersons");
    private final StringProperty image = new SimpleStringProperty(this, "image");
    private final StringProperty createdAt = new SimpleStringProperty(this, "createdAt");
    private final IntegerProperty materialItems = new SimpleIntegerProperty(this, "materialItems");
    private List<RecipeMaterial> materials = new ArrayList<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public IntegerProperty categoryIdProperty() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory.get();
    }

    public ObjectProperty<RecipeCategory> recipeCategoryProperty() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory.set(recipeCategory);
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

    public String getCustomName() {
        return customName.get();
    }

    public StringProperty customNameProperty() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName.set(customName);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getLevelId() {
        return levelId.get();
    }

    public SimpleIntegerProperty levelIdProperty() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId.set(levelId);
    }

    public RecipeLevel getRecipeLevel() {
        return recipeLevel.get();
    }

    public ObjectProperty<RecipeLevel> recipeLevelProperty() {
        return recipeLevel;
    }

    public void setRecipeLevel(RecipeLevel recipeLevel) {
        this.recipeLevel.set(recipeLevel);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public int getNumberOfPersons() {
        return numberOfPersons.get();
    }

    public SimpleIntegerProperty numberOfPersonsProperty() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons.set(numberOfPersons);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
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

    public int getMaterialItems() {
        return materialItems.get();
    }

    public IntegerProperty materialItemsProperty() {
        return materialItems;
    }

    public void setMaterialItems(int materialItems) {
        this.materialItems.set(materialItems);
    }

    public List<RecipeMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<RecipeMaterial> materials) {
        this.materials = materials;
    }
}

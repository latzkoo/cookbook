package hu.latzkoo.cookbook.model;

import javafx.beans.property.*;

public class Recipe {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty categoryId = new SimpleIntegerProperty(this, "categoryId");
    private ObjectProperty<RecipeCategory> recipeCategory = new SimpleObjectProperty<>(this, "recipeCategory");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty customName = new SimpleStringProperty(this, "customName");
    private StringProperty description = new SimpleStringProperty(this, "description");
    private SimpleIntegerProperty level = new SimpleIntegerProperty(this, "level");
    private SimpleIntegerProperty duration = new SimpleIntegerProperty(this, "duration");
    private SimpleIntegerProperty numberOfPersons = new SimpleIntegerProperty(this, "numberOfPersons");
    private StringProperty image = new SimpleStringProperty(this, "image");
    private StringProperty createdAt = new SimpleStringProperty(this, "createdAt");
    private IntegerProperty materialItems = new SimpleIntegerProperty(this, "materialItems");
    private ObjectProperty<Material> materials =
            new SimpleObjectProperty<>(this, "materials");

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

    public int getLevel() {
        return level.get();
    }

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
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

    public Material getMaterials() {
        return materials.get();
    }

    public ObjectProperty<Material> materialsProperty() {
        return materials;
    }

    public void setMaterials(Material materials) {
        this.materials.set(materials);
    }
}

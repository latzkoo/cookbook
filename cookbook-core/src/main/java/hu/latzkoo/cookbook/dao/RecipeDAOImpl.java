package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.Pager;
import hu.latzkoo.cookbook.model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    private final String connectionURL;
    private final RecipeCategoryDAO recipeCategoryDAO = new RecipeCategoryDAOImpl();

    public RecipeDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public int count(String q) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            ResultSet result;
            PreparedStatement statement;

            if (q != null) {
                String query = "SELECT count(*) AS count FROM recipe " +
                        "WHERE name LIKE ? OR customName LIKE ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, "%" + q + "%");
                statement.setString(2, "%" + q + "%");
            }
            else {
                String query = "SELECT count(*) AS count FROM recipe";
                statement = conn.prepareStatement(query);
            }

            result = statement.executeQuery();

            if (result.next()) {
                int count = result.getInt("count");
                statement.close();

                return count;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Recipe> findAll(String q, Pager pager) {
        List<Recipe> recipes = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            int idx = 1;

            String query = "SELECT r.id, r.name, r.createdAt, " +
                    "(SELECT count(rm.materialId) FROM recipe_materials AS rm WHERE recipeId=r.id) AS materials " +
                    "FROM recipe AS r";

            if (q != null) {
                query += " WHERE r.name LIKE ? OR r.customName LIKE ?";
            }
            if (pager != null) {
                query += " LIMIT ?, ?";
            }

            PreparedStatement statement = conn.prepareStatement(query);

            if (q != null) {
                statement.setString(idx++, "%" + q + "%");
                statement.setString(idx++, "%" + q + "%");
            }

            if (pager != null) {
                statement.setInt(idx++, pager.getFrom());
                statement.setInt(idx, pager.getItems());
            }

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(result.getInt("id"));
                recipe.setName(result.getString("name"));
                recipe.setMaterialItems(result.getInt("materials"));
                recipe.setCreatedAt(result.getString("createdAt"));

                recipes.add(recipe);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public Recipe findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM recipe_category WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(result.getInt("id"));
                recipe.setCategoryId(result.getInt("categoryId"));
                recipe.setName(result.getString("name"));
                recipe.setCustomName(result.getString("customName"));
                recipe.setDescription(result.getString("description"));
                recipe.setLevel(result.getInt("level"));
                recipe.setDuration(result.getInt("level"));
                recipe.setNumberOfPersons(result.getInt("numberOfPersons"));
                recipe.setImage(result.getString("image"));
                recipe.setCreatedAt(result.getString("createdAt"));

                recipe.setRecipeCategory(recipeCategoryDAO.findById(recipe.getCategoryId()));

                statement.close();

                return recipe;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Recipe save(Recipe recipe) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement;

            if (recipe.getId() <= 0) {
                statement = conn.prepareStatement("INSERT INTO recipe " +
                        "(categoryId, name, customName, description, level, duration, numberOfPersons, image, createdAt)" +
                        "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            }
            else {
                statement = conn.prepareStatement("UPDATE recipe SET " +
                        "categoryId=?, name=?, customName=?, " +
                        "description=?, level=?, duration=?, numberOfPersons=?, " +
                        "image=?, createdAt=? WHERE id=?");
                statement.setInt(10, recipe.getId());
            }

            statement.setInt(1, recipe.getRecipeCategory().getId());
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getCustomName());
            statement.setString(4, recipe.getDescription());
            statement.setInt(5, recipe.getLevel());
            statement.setInt(6, recipe.getDuration());
            statement.setInt(7, recipe.getNumberOfPersons());
            statement.setString(8, recipe.getImage());
            statement.setString(9, recipe.getCreatedAt());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            if (recipe.getId() <= 0) {
                recipe.setId(statement.getGeneratedKeys().getInt(1));
            }

            statement.close();

            return recipe;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Recipe recipe) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM recipe WHERE id=?");

            statement.setInt(1, recipe.getId());
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

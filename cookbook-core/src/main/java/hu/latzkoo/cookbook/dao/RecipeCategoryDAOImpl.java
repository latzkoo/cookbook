package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.RecipeCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeCategoryDAOImpl implements RecipeCategoryDAO {

    private final String connectionURL;

    public RecipeCategoryDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public List<RecipeCategory> findAll() {
        List<RecipeCategory> recipeCategories = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM recipe_category");

            while(result.next()) {
                RecipeCategory recipeCategory = new RecipeCategory();
                recipeCategory.setId(result.getInt("id"));
                recipeCategory.setName(result.getString("name"));

                recipeCategories.add(recipeCategory);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return recipeCategories;
    }

    @Override
    public RecipeCategory findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM recipe_category WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                RecipeCategory recipeCategory = new RecipeCategory();
                recipeCategory.setId(result.getInt("id"));
                recipeCategory.setName(result.getString("name"));

                statement.close();

                return recipeCategory;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}

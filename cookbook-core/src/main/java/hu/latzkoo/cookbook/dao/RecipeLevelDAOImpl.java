package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.RecipeLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeLevelDAOImpl implements RecipeLevelDAO {

    private final String connectionURL;

    public RecipeLevelDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public List<RecipeLevel> findAll() {
        List<RecipeLevel> recipeCategories = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM recipe_level");

            while(result.next()) {
                RecipeLevel recipeLevel = new RecipeLevel();
                recipeLevel.setId(result.getInt("id"));
                recipeLevel.setName(result.getString("name"));

                recipeCategories.add(recipeLevel);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return recipeCategories;
    }

    @Override
    public RecipeLevel findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM recipe_level WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                RecipeLevel recipeLevel = new RecipeLevel();
                recipeLevel.setId(result.getInt("id"));
                recipeLevel.setName(result.getString("name"));

                statement.close();

                return recipeLevel;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}

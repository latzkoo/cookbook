package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.RecipeMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeMaterialDAOImpl implements RecipeMaterialDAO {

    private final String connectionURL;
    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    public RecipeMaterialDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public List<RecipeMaterial> findAll(int recipeId) {
        List<RecipeMaterial> materials = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM recipe_material WHERE recipeId=?");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                RecipeMaterial recipeMaterial = new RecipeMaterial();
                recipeMaterial.setRecipeId(result.getInt("recipeId"));
                recipeMaterial.setMaterial(materialDAO.findById(result.getInt("materialId")));
                recipeMaterial.setMeasure(measureDAO.findById(result.getInt("measureId")));
                recipeMaterial.setUnit(result.getInt("unit"));

                materials.add(recipeMaterial);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return materials;
    }

    @Override
    public void insert(List<RecipeMaterial> recipeMaterials) {
        for (RecipeMaterial recipeMaterial : recipeMaterials) {
            insertRecipeMaterial(recipeMaterial);
        }
    }

    private void insertRecipeMaterial(RecipeMaterial recipeMaterial) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO recipe_material (recipeId, materialId, measureId, unit) " +
                            "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, recipeMaterial.getRecipeId());
            statement.setInt(2, recipeMaterial.getMaterial().getId());
            statement.setInt(3, recipeMaterial.getMeasure().getId());
            statement.setInt(4, recipeMaterial.getUnit());

            statement.executeUpdate();
            statement.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByRecipeId(int recipeId) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM recipe_material WHERE recipeId=?");

            statement.setInt(1, recipeId);
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

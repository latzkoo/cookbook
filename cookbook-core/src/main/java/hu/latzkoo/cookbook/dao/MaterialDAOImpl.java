package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {

    private final String connectionURL;
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    public MaterialDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM material");

            while(result.next()) {
                Material material = new Material();
                material.setId(result.getInt("id"));
                material.setMeasureId(result.getInt("measureId"));
                material.setName(result.getString("name"));
                material.setOfficialMeasureId(result.getInt("officialMeasureId"));
                material.setOfficialMeasureUnit(result.getInt("officialMeasureUnit"));
                material.setMinStock(result.getInt("minStock"));

                material.setMeasure(measureDAO.getById(material.getMeasureId()));
                material.setOfficialMeasure(measureDAO.getById(material.getOfficialMeasureId()));

                materials.add(material);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    @Override
    public Material save(Material material) {
        try(Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement statement;

            if (material.getId() <= 0) {
                statement = conn.prepareStatement("INSERT INTO material " +
                        "(measureId, name, officialMeasureUnit, officialMeasureId, minStock)" +
                        "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            }
            else {
                statement = conn.prepareStatement("UPDATE material SET " +
                        "measureId=?, name=?, officialMeasureUnit=?, " +
                        "officialMeasureId=?, minStock=? WHERE id=?");
                statement.setInt(6, material.getId());
            }

            statement.setInt(1, material.getMeasure().getId());
            statement.setString(2, material.getName());

            if (material.getOfficialMeasureUnit() != 0) {
                statement.setInt(3, material.getOfficialMeasureUnit());
            }
            else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

            if (material.getOfficialMeasure() != null) {
                statement.setInt(4, material.getOfficialMeasure().getId());
            }
            else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }

            statement.setInt(5, material.getMinStock());


            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            if (material.getId() <= 0) {
                material.setId(statement.getGeneratedKeys().getInt(1));
            }

            statement.close();

            return material;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Material material) {
        try(Connection conn = DriverManager.getConnection(connectionURL)) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM material WHERE id=?");

            statement.setInt(1, material.getId());
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

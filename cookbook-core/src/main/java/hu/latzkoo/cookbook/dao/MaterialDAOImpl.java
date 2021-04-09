package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Pager;

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
    public int count(boolean outOfStock, String q) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            ResultSet result;
            PreparedStatement statement;

            StringBuilder query = new StringBuilder("SELECT count(*) AS count FROM material WHERE 1 ");

            if (outOfStock) {
                query.append("AND stock < minStock ");
            }

            if (q != null && !q.isEmpty()) {
                query.append("AND name LIKE ? ");
            }

            query.append("ORDER BY name");

            statement = conn.prepareStatement(query.toString());

            if (q != null && !q.isEmpty()) {
                statement.setString(1, "%" + q + "%");
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
    public List<Material> findAll(boolean outOfStock) {
        List<Material> materials = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM material" +
                    (outOfStock ? " WHERE stock < minStock" : "") + " ORDER BY name");

            while(result.next()) {
                Material material = setMaterial(result);
                materials.add(material);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return materials;
    }

    @Override
    public List<Material> findAll(boolean outOfStock, String q, Pager pager) {
        List<Material> materials = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            int idx = 1;

            StringBuilder query = new StringBuilder("SELECT * FROM material WHERE 1 ");

            if (outOfStock) {
                query.append("AND stock < minStock ");
            }

            if (q != null && !q.isEmpty()) {
                query.append("AND name LIKE ? ");
            }

            query.append("ORDER BY name");

            if (pager != null) {
                query.append(" LIMIT ?, ?");
            }

            PreparedStatement statement = conn.prepareStatement(query.toString());

            if (q != null && !q.isEmpty()) {
                statement.setString(idx++, "%" + q + "%");
            }

            if (pager != null) {
                statement.setInt(idx++, pager.getFrom());
                statement.setInt(idx, pager.getItems());
            }

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Material material = setMaterial(result);
                materials.add(material);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return materials;
    }

    @Override
    public Material findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM material WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Material material = setMaterial(result);
                statement.close();

                return material;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Material setMaterial(ResultSet result) throws SQLException {
        Material material = new Material();
        material.setId(result.getInt("id"));
        material.setMeasureId(result.getInt("measureId"));
        material.setName(result.getString("name"));
        material.setCustomMeasureId(result.getInt("customMeasureId"));
        material.setOfficialMeasureId(result.getInt("officialMeasureId"));
        material.setOfficialMeasureUnit(result.getInt("officialMeasureUnit"));
        material.setMinStock(result.getInt("minStock"));
        material.setStock(result.getInt("stock"));

        material.setMeasure(measureDAO.findById(material.getMeasureId()));
        material.setCustomMeasure(measureDAO.findById(material.getCustomMeasureId()));
        material.setOfficialMeasure(measureDAO.findById(material.getOfficialMeasureId()));

        double unitStock = (double) result.getInt("stock") / material.getMeasure().getMultiplier();
        material.setMeasureUnit(unitStock);

        return material;
    }

    @Override
    public Material save(Material material) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement;

            if (material.getId() <= 0) {
                statement = conn.prepareStatement("INSERT INTO material " +
                        "(measureId, name, customMeasureId, officialMeasureUnit, officialMeasureId, minStock)" +
                        "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            } else {
                statement = conn.prepareStatement("UPDATE material SET " +
                        "measureId=?, name=?, customMeasureId=?, officialMeasureUnit=?, " +
                        "officialMeasureId=?, minStock=? WHERE id=?");
                statement.setInt(7, material.getId());
            }

            statement.setInt(1, material.getMeasure().getId());
            statement.setString(2, material.getName());

            if (material.getCustomMeasure() != null && material.getCustomMeasure().getId() > 0) {
                statement.setInt(3, material.getCustomMeasure().getId());
            }
            else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

            if (material.getOfficialMeasureUnit() != 0) {
                statement.setInt(4, material.getOfficialMeasureUnit());
            }
            else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }

            if (material.getOfficialMeasure() != null) {
                statement.setInt(5, material.getOfficialMeasure().getId());
            }
            else {
                statement.setNull(5, java.sql.Types.INTEGER);
            }

            statement.setDouble(6, material.getMinStock());


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
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Material updateStock(String event, int id, int stock) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("UPDATE material SET stock=stock"+
                    (event.equals("decrease") ? "-" : "+")+"? WHERE id=?");

            statement.setInt(1, stock);
            statement.setInt(2, id);
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return findById(id);
    }

    @Override
    public void delete(Material material) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM material WHERE id=?");

            statement.setInt(1, material.getId());
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

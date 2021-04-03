package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.Measure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeasureDAOImpl implements MeasureDAO {

    private final String connectionURL;

    public MeasureDAOImpl() {
        connectionURL = Config.getValue("database.url");
    }

    @Override
    public List<Measure> findAll() {
        List<Measure> measures = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM measure");

            while(result.next()) {
                Measure measure = new Measure();
                measure.setId(result.getInt("id"));
                measure.setCategoryId(result.getInt("categoryId"));
                measure.setName(result.getString("name"));
                measure.setMultiplier(result.getInt("multiplier"));

                measures.add(measure);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return measures;
    }

    @Override
    public Measure findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM measure WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Measure measure = new Measure();
                measure.setId(result.getInt("id"));
                measure.setCategoryId(result.getInt("categoryId"));
                measure.setName(result.getString("name"));
                measure.setMultiplier(result.getInt("multiplier"));

                statement.close();

                return measure;
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Measure save(Measure measure) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement;

            if (measure.getId() <= 0) {
                statement = conn.prepareStatement("INSERT INTO measure (categoryId, name, multiplier) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            }
            else {
                statement = conn.prepareStatement("UPDATE measure SET categoryId=?, name=?, multiplier=? WHERE id=?");
                statement.setInt(3, measure.getId());
            }

            statement.setInt(1, measure.getId());
            statement.setString(2, measure.getName());
            statement.setInt(3, measure.getMultiplier());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            if (measure.getId() <= 0) {
                measure.setId(statement.getGeneratedKeys().getInt(1));
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Measure measure) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM measure WHERE id=?");

            statement.setInt(1, measure.getId());
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

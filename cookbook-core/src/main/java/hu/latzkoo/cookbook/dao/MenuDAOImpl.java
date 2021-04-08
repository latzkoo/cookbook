package hu.latzkoo.cookbook.dao;

import hu.latzkoo.cookbook.config.Config;
import hu.latzkoo.cookbook.model.Menu;
import hu.latzkoo.cookbook.model.Pager;
import hu.latzkoo.cookbook.model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    private final String connectionURL;

    public MenuDAOImpl() {
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
                String query = "SELECT count(*) AS count FROM menu WHERE name LIKE ?";
                statement = conn.prepareStatement(query);
                statement.setString(1, "%" + q + "%");
            }
            else {
                String query = "SELECT count(*) AS count FROM menu";
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
    public List<Menu> findAll(String q, Pager pager) {
        List<Menu> menus = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            int idx = 1;

            StringBuilder query = new StringBuilder("SELECT m.id, m.name, m.duration, m.createdAt, " +
                    "(SELECT COUNT(mr.menuId) FROM menu_recipe AS mr WHERE menuId=m.id) AS recipes " +
                    "FROM menu AS m");

            if (q != null) {
                query.append(" WHERE m.name LIKE ?");
            }
            if (pager != null) {
                query.append(" LIMIT ?, ?");
            }

            PreparedStatement statement = conn.prepareStatement("PRAGMA foreign_keys = ON");
            statement.executeUpdate();
            statement = conn.prepareStatement(query.toString());

            if (q != null) {
                statement.setString(idx++, "%" + q + "%");
            }

            if (pager != null) {
                statement.setInt(idx++, pager.getFrom());
                statement.setInt(idx, pager.getItems());
            }

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Menu menu = setMenu(result);
                menus.add(menu);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return menus;
    }

    @Override
    public List<Menu> search(String name, int durationFrom, int durationTo) {
        List<Menu> menus = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            int idx = 1;

            StringBuilder query = new StringBuilder("SELECT m.id, m.name, m.duration, m.createdAt, " +
                    "(SELECT COUNT(mr.menuId) FROM menu_recipe AS mr WHERE menuId=m.id) AS recipes " +
                    "FROM menu AS m WHERE 1");

            if (name != null && !name.isEmpty()) {
                query.append(" AND m.name LIKE ?");
            }

            if (durationFrom > 0) {
                query.append(" AND m.duration>=?");
            }

            if (durationTo > 0) {
                query.append(" AND m.duration<=?");
            }

            PreparedStatement statement = conn.prepareStatement("PRAGMA foreign_keys = ON");
            statement.executeUpdate();
            statement = conn.prepareStatement(query.toString());

            if (name != null && !name.isEmpty()) {
                statement.setString(idx++, "%" + name + "%");
            }

            if (durationFrom > 0) {
                statement.setInt(idx++, durationFrom);
            }

            if (durationTo > 0) {
                statement.setInt(idx, durationTo);
            }

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Menu menu = setMenu(result);
                menus.add(menu);
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return menus;
    }

    private Menu setMenu(ResultSet result) throws SQLException {
        Menu menu = new Menu();
        menu.setId(result.getInt("id"));
        menu.setName(result.getString("name"));
        menu.setRecipeItems(result.getInt("recipes"));
        menu.setDuration(result.getInt("duration"));
        menu.setCreatedAt(result.getString("createdAt"));

        return menu;
    }

    @Override
    public Menu findById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM menu WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Menu menu = new Menu();
                menu.setId(result.getInt("id"));
                menu.setName(result.getString("name"));
                menu.setDuration(result.getInt("duration"));
                menu.setNumberOfPersons(result.getInt("numberOfPersons"));
                menu.setCreatedAt(result.getString("createdAt"));

                menu.setRecipes(getRecipes(menu.getId()));

                statement.close();

                return menu;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Recipe> getRecipes(int id) {
        List<Recipe> recipes = new ArrayList<>();
        RecipeDAO recipeDAO = new RecipeDAOImpl();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("SELECT recipeId FROM menu_recipe WHERE menuId=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                recipes.add(recipeDAO.findById(result.getInt("recipeId")));
            }

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public Menu save(Menu menu) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement;
            String query;

            if (menu.getId() <= 0) {
                query = "INSERT INTO menu (name, duration, numberOfPersons, createdAt) VALUES (?,?,?,?)";
            }
            else {
                query = "UPDATE menu SET name=?, duration=?, numberOfPersons=? WHERE id=?";
            }

            statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, menu.getName());
            statement.setInt(2, menu.getDuration());
            statement.setInt(3, menu.getNumberOfPersons());

            if (menu.getId() <= 0) {
                statement.setString(4, menu.getCreatedAt());
            }
            else {
                statement.setInt(4, menu.getId());
            }

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            if (menu.getId() <= 0) {
                menu.setId(statement.getGeneratedKeys().getInt(1));
            }

            statement.close();

            return menu;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Menu menu) {
        deleteById(menu.getId());
    }

    @Override
    public void delete(int id) {
        deleteById(id);
    }

    @Override
    public void insertRecipes(int menuId, int[] recipeIds) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);

            for (int recipeId : recipeIds) {
                if (recipeId > 0) {
                    PreparedStatement statement = conn.prepareStatement(
                            "INSERT INTO menu_recipe (menuId, recipeId) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, menuId);
                    statement.setInt(2, recipeId);

                    statement.executeUpdate();
                    statement.close();
                }
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipes(int menuId) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM menu_recipe WHERE menuId=?");

            statement.setInt(1, menuId);
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = conn.prepareStatement("PRAGMA foreign_keys = ON");
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM menu WHERE id=?");

            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialListController implements Initializable {

    private Stage stage;
    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private List<Material> materials;

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> name;

    @FXML
    private TableColumn<Material, Integer> minStock;

    @FXML
    private TableColumn<Material, Integer> stock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = materialDAO.get(true);
        setTableData();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        minStock.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    public void init(Stage stage) {
        this.stage = stage;
    }

    public void setTableData() {
        materialTable.getItems().setAll(materials);
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            closeModal();
        }
    }

    private void closeModal() {
        stage.close();
    }

}

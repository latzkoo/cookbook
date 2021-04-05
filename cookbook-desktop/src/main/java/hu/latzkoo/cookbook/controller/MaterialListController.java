package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
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
    private TableColumn<Material, String> minStock;

    @FXML
    private TableColumn<Material, String> stock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = materialDAO.findAll(true);
        setTableData();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        minStock.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                cellData.getValue().getMinStock() + " " + cellData.getValue().getMeasure().getName()));

        stock.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> {
            DecimalFormat df = new DecimalFormat("#####.##");
            return df.format(cellData.getValue().getMeasureUnit()) + " " + cellData.getValue().getMeasure().getName();
        }));
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

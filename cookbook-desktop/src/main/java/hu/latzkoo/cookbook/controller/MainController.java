package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.App;
import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private List<Material> materials;

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Measure, String> measure;

    @FXML
    private TableColumn<Material, String> name;

    @FXML
    private TableColumn<Material, String> minStock;

    @FXML
    private TableColumn<Material, String> stock;

    @FXML
    private TableColumn<Material, Void> operations;

    @FXML
    private TextField search;

    @FXML
    private VBox alertWrapper;

    @FXML
    private TextFlow alert;

    @FXML
    private Text alertMessage;

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materials = materialDAO.findAll(false);
        setTableData();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        minStock.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                cellData.getValue().getMinStock() + " " + cellData.getValue().getMeasure().getName()));
        stock.setCellValueFactory(cellData -> Bindings.createStringBinding(() ->
                cellData.getValue().getStock() + " " + cellData.getValue().getMeasure().getName()));

        operations.setCellFactory(param -> new TableCell<>(){
            private final Button btnEdit = new Button();
            private final Button btnDelete = new Button();

            {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                Image imgDelete = new Image(getClass().getResourceAsStream("/images/delete.png"));
                btnEdit.setGraphic(new ImageView(imgEdit));
                btnEdit.getStyleClass().add("btn-operation");
                btnEdit.setTooltip(new Tooltip("Szerkesztés"));
                btnDelete.setGraphic(new ImageView(imgDelete));
                btnDelete.getStyleClass().add("btn-operation");
                btnDelete.setTooltip(new Tooltip("Törlés"));

                btnEdit.setOnAction(event -> {
                    Material material = getTableRow().getItem();
                    edit(material);
                });

                btnDelete.setOnAction(event -> {
                    Material material = getTableRow().getItem();
                    delete(material);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                }
                else {
                    HBox hBox = new HBox();
                    hBox.getChildren().setAll(btnEdit, btnDelete);
                    setGraphic(hBox);
                }
            }
        });

        // Stock alert message
        setMaterialAlertMessage();
    }

    public void setMaterialAlertMessage() {
        int materialBelowMinStock = isMaterialBelowMinStock();

        if (materialBelowMinStock > 0) {
            alertMessage.setText(materialBelowMinStock + " alapanyag mennyisége a minimális szint alatt.");
        }
        else {
            alertWrapper.getChildren().removeAll(alert);
        }
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    public void setTableData() {
        materialTable.getItems().setAll(materials);
    }

    private void delete(Material material) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Biztos, hogy törli?", ButtonType.YES, ButtonType.CANCEL);

        alert.setTitle("Törlés");
        alert.setHeaderText("Törlés");
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)) {
                materialDAO.delete(material);
                setMaterials(materialDAO.findAll(false));
                setTableData();
                setMaterialAlertMessage();
            }
        });
    }

    @FXML
    private void edit(Material material) {
        showMaterialForm(material);
    }

    @FXML
    private void add() {
        showMaterialForm(new Material());
    }

    @FXML
    private void showList() {
        showMaterialList();
    }

    private void showMaterialForm(Material material) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/material_form.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();

            MaterialFormController controller = loader.getController();
            controller.init(this, stage, material);

            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);

            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

            stage.setX(bounds.getWidth() / 2 - 330);
            stage.setY(bounds.getHeight() / 2 - 230);

            scene.getStylesheets().add(String.valueOf(App.class.getResource("/css/style.css")));
            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMaterialList() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/material_list.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();

            MaterialListController controller = loader.getController();
            controller.init(stage);

            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);

            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

            stage.setX(bounds.getWidth() / 2 - 330);
            stage.setY(bounds.getHeight() / 2 - 230);

            scene.getStylesheets().add(String.valueOf(App.class.getResource("/css/style.css")));
            stage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearch() {
        List<Material> filtered = materials.stream().filter(material -> material.getName().toLowerCase()
                .contains(search.getText().toLowerCase()))
                .collect(Collectors.toList());

        materialTable.getItems().setAll(filtered);
    }

    private int isMaterialBelowMinStock() {
        int count = 0;

        for(Material material : materials) {
            if (material.getStock() < material.getMinStock()) {
                count++;
            }
        }

        return count;
    }

}

package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.App;
import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    MaterialDAO materialDAO = new MaterialDAOImpl();

    @FXML
    private TableView<Material> materialTable;

    @FXML
    private TableColumn<Material, String> id;

    @FXML
    private TableColumn<Measure, String> measure;

    @FXML
    private TableColumn<Material, String> name;

    @FXML
    private TableColumn<Material, Integer> minStock;

    @FXML
    private TableColumn<Material, Void> operations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableData();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        measure.setCellValueFactory(new PropertyValueFactory<>("measure"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        minStock.setCellValueFactory(new PropertyValueFactory<>("minStock"));
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
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    private void setTableData() {
        materialTable.getItems().setAll(materialDAO.findAll());
    }

    private void delete(Material material) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Biztos, hogy törli?", ButtonType.YES, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)) {
                materialDAO.delete(material);
                setTableData();
            }
        });
    }

    @FXML
    private void edit(Material material) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/form.fxml"));

        try {
            Parent root = loader.load();

            MaterialFormController controller = loader.getController();
            controller.setMaterial(material);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);

            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

            stage.setX(bounds.getWidth() / 2 - 330);
            stage.setY(bounds.getHeight() / 2 - 230);

            scene.getStylesheets().add(String.valueOf(App.class.getResource("/css/style.css")));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

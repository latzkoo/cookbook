package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.dao.MeasureDAO;
import hu.latzkoo.cookbook.dao.MeasureDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import hu.latzkoo.cookbook.model.TypeConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialFormController {

    private Stage stage;
    private Material material;
    private MainController parent;
    private final MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    @FXML
    private TextField name;

    @FXML
    private ComboBox<Measure> measures;

    @FXML
    private ComboBox<Measure> customMeasures;

    @FXML
    private ComboBox<Measure> officialMeasures;

    @FXML
    private TextField officialMeasureUnit;

    @FXML
    private TextField minStock;

    @FXML
    private Button btnSave;

    public void init(MainController parent, Stage stage, Material material) {
        this.parent = parent;
        this.stage = stage;
        this.material = material;
        List<Measure> measureList = measureDAO.findAll();

        name.textProperty().setValue(material.nameProperty().getValue());
        measures.valueProperty().bindBidirectional(material.measureProperty());
        measures.setItems(FXCollections.observableArrayList(
                measureList.stream()
                        .filter(m -> m.getCategoryId() != 3)
                        .collect(Collectors.toList())));

        customMeasures.valueProperty().bindBidirectional(material.customMeasureProperty());

        ObservableList<Measure> ml = FXCollections.observableArrayList(measureList.stream()
                        .filter(m -> m.getCategoryId() == 3)
                        .collect(Collectors.toList()));
        ml.add(0, new Measure());
        customMeasures.setItems(ml);

        if (material.getCustomMeasureId() > 0) {
            Measure customMeasure = material.getCustomMeasure();
            customMeasures.getSelectionModel().select(customMeasure);

            // Set additional fields status
            changeAdditionalFieldsStatus(customMeasure);
        }

        officialMeasures.valueProperty().bindBidirectional(material.officialMeasureProperty());
        officialMeasures.setItems(FXCollections.observableArrayList(
                measureList.stream()
                .filter(m -> m.getCategoryId() != 3)
                .collect(Collectors.toList())));

        if (material.getOfficialMeasureId() > 0) {
            Measure officialMeasure = material.getOfficialMeasure();
            officialMeasures.getSelectionModel().select(officialMeasure);
        }

        // Measure change event
        customMeasures.setOnAction(event -> {
            Measure selectedCustomMeasure = customMeasures.getSelectionModel().getSelectedItem();
            changeAdditionalFieldsStatus(selectedCustomMeasure);
        });

        officialMeasureUnit.textProperty().bindBidirectional(
                material.officialMeasureUnitProperty(), new TypeConverter());

        if (material.getOfficialMeasureUnit() == 0) {
            officialMeasureUnit.setText(null);
        }

        // Allow only number
        officialMeasureUnit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                officialMeasureUnit.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        minStock.textProperty().bindBidirectional(material.minStockProperty(), new TypeConverter());

        if (material.getMinStock() == 0) {
            minStock.setText(null);
        }

        // Allow only number
        minStock.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                minStock.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        btnSave.disableProperty().bind(((customMeasures.valueProperty().isNull()
                .or(customMeasures.valueProperty().asString().isEqualTo("")))
                .and(name.textProperty().isEmpty()
                .or(measures.valueProperty().isNull()
                .or(minStock.textProperty().isEmpty()))))
                .or((customMeasures.valueProperty().isNotNull()
                .and(customMeasures.valueProperty().asString().isNotEqualTo("")))
                .and(name.textProperty().isEmpty()
                .or(measures.valueProperty().isNull()
                .or(officialMeasureUnit.textProperty().isEmpty()
                .or(officialMeasures.valueProperty().isNull()
                .or(minStock.textProperty().isEmpty()))))))
        );
    }

    private void changeAdditionalFieldsStatus(Measure customMeasure) {
        if (customMeasure != null && customMeasure.getId() > 0) {
            officialMeasures.setDisable(false);
            officialMeasureUnit.setDisable(false);
        }
        else {
            officialMeasures.getSelectionModel().select(null);
            officialMeasures.setDisable(true);
            officialMeasureUnit.setDisable(true);
            officialMeasureUnit.setText(null);
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        closeModal();
    }

    @FXML
    private void onSave(ActionEvent event) {
        save();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            closeModal();
        }
        else if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            save();
        }
    }

    private void save() {
        material.setName(name.getText());

        material.setMeasure(measures.getSelectionModel().getSelectedItem());
        material.setCustomMeasure(customMeasures.getSelectionModel().getSelectedItem());
        material.setOfficialMeasure(officialMeasures.getSelectionModel().getSelectedItem());
        material.setMinStock(Integer.parseInt(minStock.getText()));

        material = materialDAO.save(material);

        parent.setMaterials(materialDAO.findAll(false));
        parent.onSearch();
        parent.setMaterialAlertMessage();
        closeModal();
    }

    private void closeModal() {
        stage.close();
    }
}

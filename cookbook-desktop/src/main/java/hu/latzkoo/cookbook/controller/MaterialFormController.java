package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.dao.MeasureDAO;
import hu.latzkoo.cookbook.dao.MeasureDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

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
    private ComboBox<Measure> officialMeasures;

    @FXML
    private TextField officialMeasureUnit;

    @FXML
    private TextField minStock;

    @FXML
    private Button btnSave;

    public void initForm(MainController parent, Stage stage, Material material) {
        this.parent = parent;
        this.stage = stage;
        this.material = material;
        List<Measure> measureList = measureDAO.get();

        name.textProperty().setValue(material.nameProperty().getValue());
        measures.valueProperty().bindBidirectional(material.measureProperty());
        measures.setItems(FXCollections.observableArrayList(measureList));

        if (material.getMeasureId() > 0) {
            Measure measure = material.getMeasure();
            measures.getSelectionModel().select(measure);

            // Set additional fields status
            changeAdditionalFieldsStatus(measure.getMeasureType());
        }

        officialMeasures.valueProperty().bindBidirectional(material.officialMeasureProperty());
        officialMeasures.setItems(FXCollections.observableArrayList(
                measureList.stream()
                .filter(m -> m.getMeasureType() == 1)
                .collect(Collectors.toList())));

        if (material.getOfficialMeasureId() > 0) {
            Measure officialMeasure = material.getOfficialMeasure();
            officialMeasures.getSelectionModel().select(officialMeasure);
        }

        // Measure change event
        measures.setOnAction(event -> {
            Measure selectedMeasure = measures.getSelectionModel().getSelectedItem();
            changeAdditionalFieldsStatus(selectedMeasure.getMeasureType());
        });

        officialMeasureUnit.textProperty().bindBidirectional(
                material.officialMeasureUnitProperty(), new NumberStringConverter());

        if (material.getOfficialMeasureUnit() == 0) {
            officialMeasureUnit.setText(null);
        }

        minStock.textProperty().bindBidirectional(
                material.minStockProperty(), new NumberStringConverter());

        if (material.getMinStock() == 0) {
            minStock.setText(null);
        }

        btnSave.disableProperty().bind(name.textProperty().isEmpty()
                .or(measures.valueProperty().isNull()
                .or(minStock.textProperty().isEmpty())));
    }

    private void changeAdditionalFieldsStatus(int measureType) {
        if (measureType == 2) {
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

        Measure measure = measures.getSelectionModel().getSelectedItem();
        material.setMeasure(measure);

        Measure officialMeasure = officialMeasures.getSelectionModel().getSelectedItem();
        material.setOfficialMeasure(officialMeasure);
        material.setMinStock(Integer.parseInt(minStock.getText()));

        material = materialDAO.save(material);

        parent.setTableData();
        closeModal();
    }

    private void closeModal() {
        stage.close();
    }
}

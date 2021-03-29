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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialFormController {

    private Material material;
    private MaterialDAO materialDAO = new MaterialDAOImpl();
    private final MeasureDAO measureDAO = new MeasureDAOImpl();

    @FXML
    private BorderPane materialForm;

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

    public void setMaterial(Material material) {
        this.material = material;
        List<Measure> measureList = measureDAO.get();

        name.textProperty().bindBidirectional(material.nameProperty());
        measures.setItems(FXCollections.observableArrayList(measureList));

        Measure measure = measureDAO.getById(material.getMeasureId());
        measures.getSelectionModel().select(measure);

        // TODO: Ezt a filtert ki kellene innen szervezni valahova
        List<Measure> officialMeasuresList = measureList.stream()
                .filter(m -> m.getMeasureType() == 1)
                .collect(Collectors.toList());

        officialMeasures.setItems(FXCollections.observableArrayList(officialMeasuresList));
        Measure officialMeasure = measureDAO.getById(material.getOfficialMeasureId());
        officialMeasures.getSelectionModel().select(officialMeasure);

        // Set additional fields status
        changeAdditionalFieldsStatus(measure.getMeasureType());

        // Select measure
        measures.setOnAction(event -> {
            Measure selectedMeasure = measures.getSelectionModel().getSelectedItem();
            changeAdditionalFieldsStatus(selectedMeasure.getMeasureType());
        });

        minStock.textProperty().bindBidirectional(
                material.minStockProperty(), new NumberStringConverter());
        officialMeasureUnit.textProperty().bindBidirectional(
                material.officialMeasureUnitProperty(), new NumberStringConverter());
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

    private void closeModal() {
        Stage stage = (Stage) materialForm.getScene().getWindow();
        stage.close();
    }

    private void save() {
        Measure measure = measures.getSelectionModel().getSelectedItem();
        material.setMeasureId(measure.getId());

        Measure officialMeasure = officialMeasures.getSelectionModel().getSelectedItem();

        if (officialMeasure != null) {
            material.setOfficialMeasureId(officialMeasure.getId());
        }

        material = materialDAO.save(material);

        closeModal();
    }
}

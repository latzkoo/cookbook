package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MeasureDAO;
import hu.latzkoo.cookbook.dao.MeasureDAOImpl;
import hu.latzkoo.cookbook.model.Material;
import hu.latzkoo.cookbook.model.Measure;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialFormController {

    private Material material;
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


        minStock.textProperty().bindBidirectional(
                material.minStockProperty(), new NumberStringConverter());
        officialMeasureUnit.textProperty().bindBidirectional(
                material.officialMeasureUnitProperty(), new NumberStringConverter());
    }
}

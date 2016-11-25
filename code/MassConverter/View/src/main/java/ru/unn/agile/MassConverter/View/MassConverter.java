package ru.unn.agile.MassConverter.View;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MassConverter.Model.MassConverter.SystemToConvert;
import ru.unn.agile.MassConverter.ViewModel.ViewModel;

public class MassConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtKilogram;
    @FXML
    private ComboBox<SystemToConvert> cmbSystemToConvert;

    @FXML
    void initialize() {
        txtKilogram.textProperty().bindBidirectional(viewModel.kilogramProperty());
        cmbSystemToConvert.valueProperty().bindBidirectional(viewModel.systemToConvertProperty());
    }
}

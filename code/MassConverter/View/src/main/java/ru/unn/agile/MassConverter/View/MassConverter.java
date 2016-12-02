package ru.unn.agile.MassConverter.View;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MassConverter.Model.MassConverter.ConversionSystem;
import ru.unn.agile.MassConverter.ViewModel.ViewModel;

public class MassConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInput;
    @FXML
    private ComboBox<ConversionSystem> cmbSystemToConvert;
    @FXML
    private ComboBox<ConversionSystem> cmbSystemFromConvert;

    @FXML
    void initialize() {
        txtInput.textProperty().bindBidirectional(viewModel.inputProperty());
        cmbSystemToConvert.valueProperty()
                .bindBidirectional(viewModel.systemToConvertProperty());
        cmbSystemFromConvert.valueProperty()
                .bindBidirectional(viewModel.systemFromConvertProperty());
    }
}

package ru.unn.agile.VolumeCalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.VolumeCalculator.viewModel.EVolumeTypes;
import ru.unn.agile.VolumeCalculator.viewModel.VolumeCalculatorViewModel;

/**
 * Created by ponom on 24.11.2016.
 */
public class VolumeCalculator {
    @FXML
    private ComboBox<EVolumeTypes> volumeTypeListBox;
    @FXML
    private Button calculateButton;
    @FXML
    private TextField param1TextField;
    @FXML
    private TextField param2TextField;
    @FXML
    private Label param1Label;
    @FXML
    private Label param2Label;
    @FXML
    private TextField volumeResultTextField;
    @FXML
    private VolumeCalculatorViewModel viewModel;
    @FXML
    private Label validationMsg;

    @FXML
    void initialize() {
        viewModel = new VolumeCalculatorViewModel();
        calculateButton.disableProperty()
                .bindBidirectional(viewModel.getCalculateDisableProperty());

        param1Label.visibleProperty().bindBidirectional(viewModel.getParam1VisibleProperty());
        param1TextField.visibleProperty().bindBidirectional(viewModel.getParam1VisibleProperty());
        param1TextField.textProperty().bindBidirectional(viewModel.getParam1ValueProperty());
        param1Label.textProperty().bindBidirectional(viewModel.getParam1Name());

        param2Label.visibleProperty().bindBidirectional(viewModel.getParam2VisibleProperty());
        param2TextField.visibleProperty().bindBidirectional(viewModel.getParam2VisibleProperty());
        param2TextField.textProperty().bindBidirectional(viewModel.getParam2ValueProperty());
        param2Label.textProperty().bindBidirectional(viewModel.getParam2Name());

        validationMsg.textProperty().bindBidirectional(viewModel.getValidationMsgProperty());

        volumeResultTextField.textProperty().bindBidirectional(viewModel.getResultVolumeProperty());

        volumeTypeListBox.valueProperty().bindBidirectional(viewModel.getSelectedItemProperty());

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}

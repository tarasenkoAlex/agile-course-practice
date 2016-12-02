package ru.unn.agile.CurrencyConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CurrencyConverter.model.Constants;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;

public class CurrencyConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField amountLb;
    @FXML
    private ComboBox<Constants> fromCurrencyBox;
    @FXML
    private ComboBox<Constants> toCurrencyBox;
    @FXML
    private Button convertButton;

    @FXML
    void initialize() {
        amountLb.textProperty().bindBidirectional(viewModel.amountProperty());

        fromCurrencyBox.valueProperty().bindBidirectional(viewModel.fromCurrencyProperty());
        toCurrencyBox.valueProperty().bindBidirectional(viewModel.toCurrencyProperty());

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}

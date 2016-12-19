package ru.unn.agile.CurrencyConverter.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CurrencyConverter.model.Constants;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;
import ru.unn.agile.CurrencyConverter.infrastructure.TxtLogger;

import java.io.IOException;

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
    void initialize() throws IOException {
        viewModel.setLogger(new TxtLogger("./TxtLogger.log"));

        amountLb.textProperty().bindBidirectional(viewModel.amountProperty());
        amountLb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                try {
                    viewModel.onAmountChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fromCurrencyBox.valueProperty().bindBidirectional(viewModel.fromCurrencyProperty());
        fromCurrencyBox.valueProperty().addListener(new ChangeListener<Constants>() {
            @Override
            public void changed(final ObservableValue<? extends Constants> observable,
                                final Constants oldValue, final Constants newValue) {
                try {
                    viewModel.onFromCurrencyChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toCurrencyBox.valueProperty().bindBidirectional(viewModel.toCurrencyProperty());
        toCurrencyBox.valueProperty().addListener(new ChangeListener<Constants>() {
            @Override
            public void changed(final ObservableValue<? extends Constants> observable,
                                final Constants oldValue, final Constants newValue) {
                try {
                    viewModel.onToCurrencyChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                try {
                    viewModel.convert();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

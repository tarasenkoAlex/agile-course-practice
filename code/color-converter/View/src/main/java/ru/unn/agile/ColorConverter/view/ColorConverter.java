package ru.unn.agile.ColorConverter.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.ColorConverter.infrastructure_colorConverter.ColorConverterLogger;
import ru.unn.agile.ColorConverter.model.ColorSpaces;
import ru.unn.agile.ColorConverter.viewmodel.ViewModel;

import java.io.IOException;


public class ColorConverter {
    @FXML
    private TextField firstValue;
    @FXML
    private TextField secondValue;
    @FXML
    private TextField thirdValue;
    @FXML
    private ComboBox<ColorSpaces> toSomeColor;
    @FXML
    private ComboBox<ColorSpaces> fromSomeColor;
    @FXML
    private Button convertButton;
    @FXML
    private ViewModel viewModel;

    @FXML
    void initialize() throws IOException {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        viewModel.setLogger(new ColorConverterLogger("./TxtLogger-lab3.log"));
        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                try {
                    viewModel.onFocusChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        firstValue.textProperty().bindBidirectional(viewModel.getFirstValueProperty());
        firstValue.focusedProperty().addListener(focusChangeListener);
        secondValue.textProperty().bindBidirectional(viewModel.getSecondValueProperty());
        secondValue.focusedProperty().addListener(focusChangeListener);
        thirdValue.textProperty().bindBidirectional(viewModel.getThirdValueProperty());
        thirdValue.focusedProperty().addListener(focusChangeListener);
        fromSomeColor.valueProperty().bindBidirectional(viewModel.getFromColorSpace());
        fromSomeColor.valueProperty().addListener(new ChangeListener<ColorSpaces>() {
            @Override
            public void changed(final ObservableValue<? extends ColorSpaces> observable,
                                final ColorSpaces olSourceValue,
                                final ColorSpaces newSourceValue) {
                try {
                    viewModel.onColorSpaceChanged(olSourceValue, newSourceValue, Boolean.TRUE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toSomeColor.valueProperty().bindBidirectional(viewModel.getToColorSpace());
        toSomeColor.valueProperty().addListener(new ChangeListener<ColorSpaces>() {
            @Override
            public void changed(final ObservableValue<? extends ColorSpaces> observable,
                                final ColorSpaces oldDestinationValue,
                                final ColorSpaces newDestinationValue) {
                try {
                    viewModel.onColorSpaceChanged(oldDestinationValue, newDestinationValue,
                            Boolean.FALSE);
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

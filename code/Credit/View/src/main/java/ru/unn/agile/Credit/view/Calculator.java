package ru.unn.agile.Credit.view;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.Credit.viewmodel.ViewModel;
import ru.unn.agile.Credit.infrastructure.TxtLogger;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField sumTextField;
    @FXML
    private TextField monthsTextField;
    @FXML
    private TextField percentTextField;
    @FXML
    private TextField paymentTextField;
    @FXML
    private TextField overpaymentTextField;
    @FXML
    private TextField totalSumTextField;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btnClear;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> focusChangeListener =
                (observable, oldValue, newValue) -> viewModel.onFocusChanged(oldValue, newValue);

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind

        sumTextField.textProperty().bindBidirectional(viewModel.sumProperty());
        sumTextField.focusedProperty().addListener(focusChangeListener);
        monthsTextField.textProperty().bindBidirectional(viewModel.monthsProperty());
        monthsTextField.focusedProperty().addListener(focusChangeListener);
        percentTextField.textProperty().bindBidirectional(viewModel.percentProperty());
        percentTextField.focusedProperty().addListener(focusChangeListener);


        totalSumTextField.textProperty().bind(viewModel.totalSumProperty());
        paymentTextField.textProperty().bind(viewModel.paymentProperty());
        overpaymentTextField.textProperty().bind(viewModel.overpaymentProperty());


        //Clear button handler
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.clear();
            }
        });

        //Calculate button handler
        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}

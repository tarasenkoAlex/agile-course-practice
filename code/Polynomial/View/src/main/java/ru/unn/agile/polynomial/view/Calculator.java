package ru.unn.agile.polynomial.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class Calculator {
    @FXML
    private Label secondOperandLabel;
    @FXML
    private TextField firstOperandTextField;
    @FXML
    private TextField secondOperandTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private ChoiceBox operationsChoiceBox;
    @FXML
    private Button calculationButton;
    @FXML
    private Label statusLabel;
}

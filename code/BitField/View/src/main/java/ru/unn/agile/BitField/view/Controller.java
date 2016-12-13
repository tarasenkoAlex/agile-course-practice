package ru.unn.agile.BitField.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.BitField.viewmodel.ViewModel;

public class Controller {
    ObservableList<String> numBitList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7");

    // Field A

    @FXML
    private Button inputAButton;

    @FXML
    private TextField inputATextField;

    @FXML
    private ComboBox<String> setBitAComboBox;

    @FXML
    private Button setBitAButton;

    @FXML
    private Button getBitAButton;

    @FXML
    private TextField showATextField;

    @FXML
    private TextField showChooseBitATextField;

    @FXML
    private Button notAButton;

    // Field B

    @FXML
    private Button inputBButton;

    @FXML
    private TextField inputBTextField;

    @FXML
    private ComboBox<String> setBitBComboBox;

    @FXML
    private Button setBitBButton;

    @FXML
    private Button getBitBButton;

    @FXML
    private TextField showBTextField;

    @FXML
    private TextField showChooseBitBTextField;

    @FXML
    private Button notBButton;

    // Logic operations

    @FXML
    private Button logicAndButton;

    @FXML
    private Button logicOrButton;

    @FXML
    private Button logicXorButton;

    @FXML
    private TextField showResultLogicTextField;

    //

    //ViewModel viewModel = new ViewModel();

    @FXML
    private ViewModel viewModel;

    @FXML
    void initialize() {
        setBitAComboBox.setItems(numBitList);
        setBitBComboBox.setItems(numBitList);

        setBitAComboBox.setValue("0");
        setBitBComboBox.setValue("0");
    }

    /*
    private void backBind(int numOfField) {
        viewModel.setBitField(inputATextField.getText(), numOfField);
    }

    private void bind(int numOfField) {
        inputAButton.setDisable(viewModel.isInputAButtonEnabled(numOfField));
    }
    */
}

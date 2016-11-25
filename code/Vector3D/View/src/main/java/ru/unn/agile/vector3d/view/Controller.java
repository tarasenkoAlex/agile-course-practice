package ru.unn.agile.vector3d.view;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.fxml.FXML;
import ru.unn.agile.vector3d.viewmodel.ViewModel;

public class Controller {
    @FXML
    private TabPane opsTabPane;
    @FXML
    private Tab normTab;
    @FXML
    private Tab normalizationTab;
    @FXML
    private Tab dotProductTab;
    @FXML
    private Tab crossProductTab;
    @FXML
    private TextField vectorTextField;
    @FXML
    private TextField normResultTextField;
    @FXML
    private TextField normalizationResultTextField;
    @FXML
    private TextField dotProductOperandTextField;
    @FXML
    private TextField dotProductResultTextField;
    @FXML
    private TextField crossProductOperandTextField;
    @FXML
    private TextField crossProductResultTextField;
    @FXML
    private Button calculateButton;
}

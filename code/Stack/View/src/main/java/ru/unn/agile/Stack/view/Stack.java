package ru.unn.agile.Stack.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Stack {

    @FXML
    private Button popbutton;
    @FXML
    private Button pushbutton;
    @FXML
    private Button topbutton;
    @FXML
    private Button printbutton;
    @FXML
    private TextField txtinput;
    @FXML
    private TextArea txtprint;
    @FXML
    private TextArea txtlog;
    @FXML
    private Button isemptybutton;
    @FXML
    private TextArea txtmsg;

    @FXML
    void initialize() {
        popbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                //viewModel.calculate();
                int a = 0;
                a /= a;
            }
        });
    }
}

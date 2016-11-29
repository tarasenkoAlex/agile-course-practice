package ru.unn.agile.Stack.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Stack {

    public Button popbutton;
    public Button pushbutton;
    public Button topbutton;
    public Button printbutton;
    public TextField txtinput;
    public TextArea txtprint;
    public TextArea txtlog;
    public Button isemptybutton;
    public TextArea txtmsg;

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

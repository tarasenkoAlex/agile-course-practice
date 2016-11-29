package ru.unn.agile.Stack.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import ru.unn.agile.Stack.model;

public class ViewModel {
    private final StringProperty txtinput = new SimpleStringProperty();
    private final StringProperty txtprint = new SimpleStringProperty();
    private final StringProperty txtlog = new SimpleStringProperty();
    private final StringProperty txtmsg = new SimpleStringProperty();

    private Stack stk;

    public StringProperty txtinputProperty() {
        return txtinput;
    }

    public StringProperty txtprintProperty() {
        return txtprint;
    }

    public StringProperty txtlogProperty() {
        return txtlog;
    }

    public StringProperty txtmsgProperty() {
        return txtmsg;
    }

    public ViewModel() {
        txtinput.set("");
        txtprint.set("");
        txtlog.set("");
        txtmsg.set("");
    }

    public void push() {

    }
}

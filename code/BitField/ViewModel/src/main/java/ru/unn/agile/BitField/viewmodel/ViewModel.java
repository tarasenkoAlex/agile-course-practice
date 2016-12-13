package ru.unn.agile.BitField.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private boolean inputButtonEnabled[] = {false, false};

    private final BooleanProperty ibdis = new SimpleBooleanProperty();

    public ViewModel() {
        ibdis.set(true);
    }

    public BooleanProperty ibdisProperty() {
        return ibdis;
    }

    public final boolean getibdis() {
        return ibdis.get();
    }

    public boolean isInputAButtonEnabled(int numOfField) {
        return inputButtonEnabled[numOfField];
    }

    public void setBitField(String bitField, int numOfField) {
        inputButtonEnabled[numOfField] = true;
    }
}


package ru.unn.agile.BitField.viewmodel;

import com.sun.xml.internal.fastinfoset.algorithm.IntegerEncodingAlgorithm;
import com.sun.xml.internal.ws.server.ServerRtException;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.BitField.model.BitField;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private BitField bitFieldA = new BitField(8);
    private BitField bitFieldB = new BitField(8);

    private final BooleanProperty inputButtonDisabledA = new SimpleBooleanProperty();
    private final StringProperty bitFieldStringA = new SimpleStringProperty();
    private final StringProperty chooseBitA = new SimpleStringProperty();
    private final StringProperty textErrorA = new SimpleStringProperty();
/*
    private final BooleanProperty inputBButtonDisabled = new SimpleBooleanProperty();
    private final StringProperty bitFieldBString = new SimpleStringProperty();
    private final StringProperty chooseBitB = new SimpleStringProperty();
    private final StringProperty textErrorB = new SimpleStringProperty();
*/
    public ViewModel() {
        inputButtonDisabledA.set(true);
        bitFieldStringA.set(bitFieldA.toString());
    }

    // Field A Methods

    public void setBitFieldStringA(String bitField) {
        String correctBitField = CorrectionBitField(bitField);

        bitFieldA = BitField.fromString(correctBitField);
        bitFieldStringA.set(bitFieldA.toString());
    }

    public void inputBitFieldA(String bitField) {
        if(bitField.equals("")) {
            inputButtonDisabledA.set(true);
            return;
        }

        if(!bitField.matches("[01]+")) {
            inputButtonDisabledA.set(true);
            textErrorA.set("Only 0 and 1");
            return;
        }

        int lenBitField = bitField.length();
        if(lenBitField > 8) {
            inputButtonDisabledA.set(true);
            textErrorA.set("Length of BitField must be less or equal 8");
            return;
        }

        textErrorA.set("");
        inputButtonDisabledA.set(false);
    }

    public void setBitFieldBitA(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldA.setBit(numOfBitInt);

        bitFieldStringA.set(bitFieldA.toString());
    }

    public void clearBitFieldBitA(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldA.clrBit(numOfBitInt);

        bitFieldStringA.set(bitFieldA.toString());
    }

    public void getBitFieldBitA(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        int chooseBit = bitFieldA.getBit(numOfBitInt);

        chooseBitA.set(Integer.toString(chooseBit));
    }

    public void logicNotA() {
        BitField field = new BitField(bitFieldA);
        bitFieldA = field.not();

        bitFieldStringA.set(bitFieldA.toString());
    }

    private String CorrectionBitField(final String bitField) {
        String correctBitField = new String();
        int lenBitField = bitField.length();

        for(int i = 0; i < 8 - lenBitField; i++)
            correctBitField += "0";

        correctBitField += bitField;
        return correctBitField;
    }

    // Property Getters Field A

    public BooleanProperty inputButtonDisabledAProperty() {
        return inputButtonDisabledA;
    }

    public StringProperty bitFieldStringAProperty() {
        return bitFieldStringA;
    }

    public StringProperty chooseBitAProperty() {
        return chooseBitA;
    }

    public StringProperty textErrorAProperty() {
        return textErrorA;
    }

    public final boolean getInputButtonDisabledA() {
        return inputButtonDisabledA.get();
    }

    public final String getBitFieldStringA() {
        return bitFieldStringA.get();
    }

    public final String getChooseBitA() {
        return chooseBitA.get();
    }

    public final String getTextErrorA() {
        return textErrorA.get();
    }
}


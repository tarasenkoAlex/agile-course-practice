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

    private final BooleanProperty inputAButtonDisabled = new SimpleBooleanProperty();
    private final StringProperty bitFieldAString = new SimpleStringProperty();
    private final StringProperty chooseBitdA = new SimpleStringProperty();
    private final StringProperty textErrorA = new SimpleStringProperty();

    public ViewModel() {
        inputAButtonDisabled.set(true);
        bitFieldAString.set(bitFieldA.toString());
    }

    // Field A Methods

    public void setABitFieldString(String bitField) {
        int lenBitField = bitField.length();
        if(lenBitField > 8) {
            textErrorA.set("Lenght of BitField must be less or equal 8");
            return;
        }
        textErrorA.set("");

        String correctBitField = CorrectionBitField(bitField, lenBitField);

        bitFieldA = BitField.fromString(correctBitField);
        bitFieldAString.set(bitFieldA.toString());
    }

    public void inputABitField(String bitField) {
        if(bitField.equals("")) {
            inputAButtonDisabled.set(true);
            return;
        }

        if(!bitField.matches("[01]+")) {
            inputAButtonDisabled.set(true);
            textErrorA.set("Only 0 and 1");
            return;
        }

        textErrorA.set("");
        inputAButtonDisabled.set(false);
    }

    public void setABitFieldBit(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldA.setBit(numOfBitInt);

        bitFieldAString.set(bitFieldA.toString());
    }

    public void clearABitFieldBit(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldA.clrBit(numOfBitInt);

        bitFieldAString.set(bitFieldA.toString());
    }

    public void getABitFieldBit(String numOfBit) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        int chooseBit = bitFieldA.getBit(numOfBitInt);

        chooseBitdA.set(Integer.toString(chooseBit));
    }

    public void logicNotA() {
        BitField field = new BitField(bitFieldA);
        bitFieldA = field.not();

        bitFieldAString.set(bitFieldA.toString());
    }

    private String CorrectionBitField(final String bitField, final int lenBitField) {
        String correctBitField = new String();
        for(int i = 0; i < 8 - lenBitField; i++)
            correctBitField += "0";

        correctBitField += bitField;
        return correctBitField;
    }

    // Property Getters

    public BooleanProperty inputAButtonDisabledProperty() {
        return inputAButtonDisabled;
    }

    public StringProperty bitFieldAStringProperty() {
        return bitFieldAString;
    }

    public StringProperty getChooseBitdAProperty() {
        return chooseBitdA;
    }

    public StringProperty textErrorAProperty() {
        return textErrorA;
    }

    public final boolean getInputAButtonDisabled() {
        return inputAButtonDisabled.get();
    }

    public final String getBitFieldAString() {
        return bitFieldAString.get();
    }

    public final String getChooseBitA() {
        return chooseBitdA.get();
    }

    public final String getTextErrorA() {
        return textErrorA.get();
    }
}


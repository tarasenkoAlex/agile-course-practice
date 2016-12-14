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
    private BitField bitFieldResult = new BitField(8);

    private final StringProperty bitFieldStringA = new SimpleStringProperty();
    private final StringProperty chooseBitA = new SimpleStringProperty();
    private final StringProperty textErrorA = new SimpleStringProperty();

    private final StringProperty bitFieldStringB = new SimpleStringProperty();
    private final StringProperty chooseBitB = new SimpleStringProperty();
    private final StringProperty textErrorB = new SimpleStringProperty();

    private final StringProperty resultText = new SimpleStringProperty();

    public ViewModel() {
        bitFieldStringA.set(bitFieldA.toString());
        bitFieldStringB.set(bitFieldB.toString());
    }

    // Methods

    public void setBitFieldStringA(String bitField) {
        if(canInputBitFieldCur(bitField, textErrorA) == false) {
            return;
        }

        String correctBitField = CorrectionBitField(bitField);

        bitFieldA = BitField.fromString(correctBitField);
        bitFieldStringA.set(bitFieldA.toString());
    }

    public void setBitFieldStringB(String bitField) {
        if(canInputBitFieldCur(bitField, textErrorB) == false) {
            return;
        }

        String correctBitField = CorrectionBitField(bitField);

        bitFieldB = BitField.fromString(correctBitField);
        bitFieldStringB.set(bitFieldB.toString());
    }

    public void setBitFieldBitA(String numOfBit) {
        setBitFieldBitCur(numOfBit, bitFieldA, bitFieldStringA);
    }

    public void setBitFieldBitB(String numOfBit) {
        setBitFieldBitCur(numOfBit, bitFieldB, bitFieldStringB);
    }

    public void clearBitFieldBitA(String numOfBit) {
        clearBitFieldBitCur(numOfBit, bitFieldA, bitFieldStringA);
    }

    public void clearBitFieldBitB(String numOfBit) {
        clearBitFieldBitCur(numOfBit, bitFieldB, bitFieldStringB);
    }

    public void getBitFieldBitA(String numOfBit) {
        getBitFieldBitCur(numOfBit, bitFieldA, chooseBitA);
    }

    public void getBitFieldBitB(String numOfBit) {
        getBitFieldBitCur(numOfBit, bitFieldB, chooseBitB);
    }

    public void logicNotA() {
        BitField field = new BitField(bitFieldA);
        bitFieldA = field.not();

        bitFieldStringA.set(bitFieldA.toString());
    }

    public void logicNotB() {
        BitField field = new BitField(bitFieldB);
        bitFieldB = field.not();

        bitFieldStringB.set(bitFieldB.toString());
    }

    private String CorrectionBitField(final String bitField) {
        String correctBitField = new String();
        int lenBitField = bitField.length();

        for(int i = 0; i < 8 - lenBitField; i++)
            correctBitField += "0";

        correctBitField += bitField;
        return correctBitField;
    }

    private boolean canInputBitFieldCur(String bitField, StringProperty textErrorCur) {
        if(bitField.equals("")) {
            textErrorCur.set("Text Field is Empty");
            return false;
        }

        if(!bitField.matches("[01]+")) {
            textErrorCur.set("Only 0 and 1");
            return false;
        }

        int lenBitField = bitField.length();
        if(lenBitField > 8) {
            textErrorCur.set("Length of BitField must be less or equal 8");
            return false;
        }

        textErrorCur.set("");
        return true;
    }

    public void setBitFieldBitCur(String numOfBit, BitField bitFieldCur, StringProperty bitFieldStringCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldCur.setBit(numOfBitInt);

        bitFieldStringCur.set(bitFieldCur.toString());
    }

    public void clearBitFieldBitCur(String numOfBit, BitField bitFieldCur, StringProperty bitFieldStringCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        bitFieldCur.clrBit(numOfBitInt);

        bitFieldStringCur.set(bitFieldCur.toString());
    }

    public void getBitFieldBitCur(String numOfBit, BitField bitFieldCur, StringProperty chooseBitCur) {
        int numOfBitInt = Integer.parseInt(numOfBit);
        int chooseBit = bitFieldCur.getBit(numOfBitInt);

        chooseBitCur.set(Integer.toString(chooseBit));
    }

    public void logicAAndB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.and(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    public void logicAOrB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.or(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    public void logicAXorB() {
        bitFieldResult = new BitField(bitFieldA);
        bitFieldResult = bitFieldA.xor(bitFieldB);

        resultText.set(bitFieldResult.toString());
    }

    // Property Getters Fields

    public StringProperty bitFieldStringAProperty() {
        return bitFieldStringA;
    }

    public StringProperty bitFieldStringBProperty() {
        return bitFieldStringB;
    }

    public StringProperty chooseBitAProperty() {
        return chooseBitA;
    }

    public StringProperty chooseBitBProperty() {
        return chooseBitB;
    }

    public StringProperty textErrorAProperty() {
        return textErrorA;
    }

    public StringProperty textErrorBProperty() {
        return textErrorB;
    }

    public StringProperty resultTextProperty() {
        return resultText;
    }

    // Getters Fields

    public final String getBitFieldStringA() {
        return bitFieldStringA.get();
    }

    public final String getBitFieldStringB() {
        return bitFieldStringB.get();
    }

    public final String getChooseBitA() {
        return chooseBitA.get();
    }

    public final String getChooseBitB() {
        return chooseBitB.get();
    }

    public final String getTextErrorA() {
        return textErrorA.get();
    }

    public final String getTextErrorB() {
        return textErrorB.get();
    }

    public final String getResultText() {
        return resultText.get();
    }
}


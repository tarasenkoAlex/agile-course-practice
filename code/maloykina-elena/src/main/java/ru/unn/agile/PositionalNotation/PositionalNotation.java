package ru.unn.agile.PositionalNotation;

public abstract class PositionalNotation {
    public int convertToDecimal(){
        return 0;
    }
    public StringBuilder convertToBinary(){
        int decimal = convertToDecimal();
        StringBuilder result = new Decimal(decimal).convertToBinary();
        return result;
    }
    public StringBuilder convertToOctal(){
        int decimal = convertToDecimal();
        StringBuilder result = new Decimal(decimal).convertToOctal();
        return result;
    }
    public StringBuilder convertToHex(){
        int decimal = convertToDecimal();
        StringBuilder result = new Decimal(decimal).convertToHex();
        return result;
    }
}

package ru.unn.agile.PositionalNotation;

public class Converter {
    public StringBuilder binaryToOctal(final StringBuilder binary) {
        int decimal = new ToDecimalConverter(binary).convertBinaryToDecimal();
        return new FromDecimalConverter(decimal).convertToOctal();
    }
    public int binaryToDecimal(final StringBuilder binary) {
        return new ToDecimalConverter(binary).convertBinaryToDecimal();
    }
    public StringBuilder binaryToHex(final StringBuilder binary) {
        int decimal = new ToDecimalConverter(binary).convertBinaryToDecimal();
        return new FromDecimalConverter(decimal).convertToHex();
    }
    public StringBuilder octalToBinary(final StringBuilder octal) {
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        return  new FromDecimalConverter(decimal).convertToBinary();
    }
    public int octalToDecimal(final StringBuilder octal) {
        return new ToDecimalConverter(octal).convertOctalToDecimal();
    }
    public StringBuilder octalToHex(final StringBuilder octal) {
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        return  new FromDecimalConverter(decimal).convertToHex();
    }
    public StringBuilder decimalToBinary(final int decimal) {
        return new FromDecimalConverter(decimal).convertToBinary();
    }
    public StringBuilder decimalToOctal(final int decimal) {
        return new FromDecimalConverter(decimal).convertToOctal();
    }
    public StringBuilder decimalToHex(final int decimal) {
        return new FromDecimalConverter(decimal).convertToHex();
    }
    public StringBuilder hexToBinary(final StringBuilder hex) {
        int decimal = new ToDecimalConverter(hex).convertHexToDecimal();
        return new FromDecimalConverter(decimal).convertToBinary();
    }
    public StringBuilder hexToOctal(final StringBuilder hex) {
        int decimal = new ToDecimalConverter(hex).convertHexToDecimal();
        return new FromDecimalConverter(decimal).convertToOctal();
    }
    public int hexToDecimal(final StringBuilder hex) {
        return new ToDecimalConverter(hex).convertHexToDecimal();
    }
}

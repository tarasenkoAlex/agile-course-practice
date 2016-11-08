package ru.unn.agile.PositionalNotation;

public class Octal extends PositionalNotation{
    StringBuilder octalNumber;

    public Octal(StringBuilder octalNumber) {
        this.octalNumber = octalNumber;
    }
    public void checkOctalValue(){
        for (int i = 0; i < octalNumber.length(); i++){
            if (octalNumber.charAt(i) > '7' || octalNumber.charAt(i) < '0'){
                throw new IllegalArgumentException("Incorrect octal number");
            }
        }
    }
    @Override
    public int convertToDecimal(){
        int result = 0;int iter = 0;
        StringBuilder binary = octalNumber.reverse();
        for (int i = 0; i < binary.length();i++) {
            char elem = binary.charAt(i);
            int val = ((elem - '0') << iter);
            iter +=3;
            result += val;
        }
        return result;
    }/*
    public StringBuilder convertToBinary(){
        int decimal = convertToDecimal();
        StringBuilder result = new Decimal(decimal).convertToBinary();
        return result;
    }
    public StringBuilder convertToHex(){
        int decimal = convertToDecimal();
        StringBuilder result = new Decimal(decimal).convertToHex();
        return result;
    }*/
}


package ru.unn.agile.PositionalNotation;

public class Decimal extends PositionalNotation{
    private int decimalNumber;
    public Decimal(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public void checkDecimalValue(){
        if (decimalNumber < 0) {
            throw new IllegalArgumentException("Enter positive number");
        }
    }
    @Override
    public StringBuilder convertToBinary(){
        StringBuilder result = new StringBuilder();
        int decimal = decimalNumber;
        do {
            result.append(decimal & 0x01);
            decimal >>>= 1;
        }while (decimal != 0);
        return result.reverse();
    }
    @Override
    public StringBuilder convertToOctal(){
        StringBuilder result = new StringBuilder();
        int decimal = decimalNumber;
        do{
            result.append(Constatants.octals[(int)(decimal & 0x07)]);
            decimal >>>= 3;
        }while(decimal != 0);
        return result.reverse();
    }
    @Override
    public StringBuilder convertToHex(){
        StringBuilder result = new StringBuilder();
        int decimal = decimalNumber;
        int iter = 0;
        do {
            iter++;
            result.append(Constatants.hex[(int)(decimal & 0xf)]);
            decimal >>>= 4;
        }while(decimal != 0);
        if (iter > 1) {
            return result.reverse();
        }else{
            return result;
        }
    }
}

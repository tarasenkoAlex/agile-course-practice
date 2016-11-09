package ru.unn.agile.PositionalNotation;

public class FromDecimalConverter {
    private final int number;
    public FromDecimalConverter(final int decimal) {
        this.number = decimal;
    }

    public StringBuilder convertToBinary() {
        StringBuilder result = new StringBuilder();
        int decimal = number;
        do {
            result.append(decimal & Constatants.BINARY_MULT);
            decimal >>>= 1;
        } while (decimal != 0);
        return result.reverse();
    }
    public StringBuilder convertToOctal() {
        StringBuilder result = new StringBuilder();
        int decimal = number;
        do {
            result.append(Constatants.OCTALS[(int) (decimal & Constatants.OCTAL_MULT)]);
            decimal >>>= Constatants.OCTAL_ITER;
        } while (decimal != 0);
        return result.reverse();
    }
    public StringBuilder convertToHex() {
        StringBuilder result = new StringBuilder();
        int decimal = number;
        int iter = 0;
        do {
            iter++;
            result.append(Constatants.HEX[(int) (decimal & Constatants.HEX_MULT)]);
            decimal >>>= Constatants.HEX_ITER;
        } while (decimal != 0);
        if (iter > 1) {
            return result.reverse();
        } else {
            return result;
        }
    }
}

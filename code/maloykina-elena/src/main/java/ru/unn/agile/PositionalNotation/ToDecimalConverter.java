package ru.unn.agile.PositionalNotation;

public class ToDecimalConverter {
    private final StringBuilder number;
    public ToDecimalConverter(final StringBuilder number) {
        this.number = number;
    }

    public int convertBinaryToDecimal() {
        int result = 0;
        int iter = 0;
        StringBuilder binary = number.reverse();
        for (int i = 0; i < binary.length(); i++) {
            char elem = binary.charAt(i);
            result += ((elem - '0') << iter++);
        }
        return result;
    }
    public int convertOctalToDecimal() {
        int result = 0;
        int iter = 0;
        StringBuilder octal = number.reverse();
        for (int i = 0; i < octal.length(); i++) {
            char elem = octal.charAt(i);
            int val = ((elem - '0') << iter);
            iter += Constatants.OCTAL_ITER;
            result += val;
        }
        return result;
    }
    public int convertHexToDecimal() {
        int result = 0;
        int iter = 0;
        int val;
        StringBuilder hex = number.reverse();
        for (int i = 0; i < hex.length(); i++) {
            char elem = hex.charAt(i);
            if (elem == 'a') {
                val = Constatants.HEX_A << iter;
            } else if (elem > 'a') {
                val = (Constatants.HEX_A + elem - 'a') << iter;
            } else {
                val = ((elem - '0') << iter);
            }
            iter += Constatants.HEX_ITER;
            result += val;
        }
        return result;
    }
}

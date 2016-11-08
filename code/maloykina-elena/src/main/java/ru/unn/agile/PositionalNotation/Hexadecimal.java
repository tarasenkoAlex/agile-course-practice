package ru.unn.agile.PositionalNotation;

public class Hexadecimal extends PositionalNotation{
    StringBuilder hexadecimalNumber;
    public Hexadecimal(StringBuilder hexadecimalNumber) {
        this.hexadecimalNumber = hexadecimalNumber;
    }
    public void checkHexValue(){
        for (int i = 0; i < hexadecimalNumber.length(); i++){
            if (!((hexadecimalNumber.charAt(i) <= '9' && hexadecimalNumber.charAt(i) >= '0') ||
                    (hexadecimalNumber.charAt(i) <= 'f' && hexadecimalNumber.charAt(i) >= 'a'))){
                throw new IllegalArgumentException("Incorrect hex number");
            }
        }
    }
    @Override
    public int convertToDecimal(){
        int result = 0;
        int iter = 0;
        int val;
        StringBuilder binary = hexadecimalNumber.reverse();
        for (int i = 0; i < binary.length();i++) {
            char elem = binary.charAt(i);
            if (elem == 'a'){
                val = 10 << iter;
            }else if (elem > 'a'){
                val = (10 + elem - 'a')<<iter;
            }else{
                val = ((elem - '0') << iter);
            }
            iter += 4;
            result += val;
        }
        return result;
    }
}

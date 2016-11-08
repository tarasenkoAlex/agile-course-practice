package ru.unn.agile.PositionalNotation;

public class Binary extends PositionalNotation{
    private StringBuilder binaryNumber;
    public Binary(StringBuilder binaryNumber) {
        this.binaryNumber = binaryNumber;
    }

    public void checkBinaryValue(){
        for (int i = 0; i < binaryNumber.length(); i++){
            if (binaryNumber.charAt(i) > '1' || binaryNumber.charAt(i) < '0'){
                throw new IllegalArgumentException("Incorrect binary number");
            }
        }
    }
    @Override
    public int convertToDecimal(){
        int result = 0;
        int iter = 0;
        StringBuilder binary = binaryNumber.reverse();
        for (int i = 0; i < binary.length();i++) {
            char elem = binary.charAt(i);
            result += ((elem - '0') << iter++);
        }
        return result;
    }
}

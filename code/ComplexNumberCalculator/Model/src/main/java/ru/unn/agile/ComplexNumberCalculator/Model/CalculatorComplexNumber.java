package ru.unn.agile.ComplexNumberCalculator.Model;

/**
 * Created by Дарья on 07.11.2016.
 */
public final class CalculatorComplexNumber {
    private CalculatorComplexNumber() {
    }

    public static ComplexNum add(final ComplexNum complexNumA, final ComplexNum complexNumB) {
        checkNonNullNumber(complexNumA);
        checkNonNullNumber(complexNumB);
        return new ComplexNum(complexNumA.getReal() + complexNumB.getReal(),
                complexNumA.getImaginary() + complexNumB.getImaginary());
    }

    public static ComplexNum sub(final ComplexNum complexNumA, final ComplexNum complexNumB) {
        checkNonNullNumber(complexNumA);
        checkNonNullNumber(complexNumB);
        return new ComplexNum(complexNumA.getReal() - complexNumB.getReal(),
                complexNumA.getImaginary() - complexNumB.getImaginary());
    }

    public static ComplexNum mult(final ComplexNum complexNumA, final ComplexNum complexNumB) {
        checkNonNullNumber(complexNumA);
        checkNonNullNumber(complexNumB);
        float real = complexNumA.getReal() * complexNumB.getReal()
                - complexNumA.getImaginary() * complexNumB.getImaginary();
        float im = complexNumA.getReal() * complexNumB.getImaginary()
                + complexNumA.getImaginary() * complexNumB.getReal();
        return new ComplexNum(real, im);
    }

    private static void checkNonZeroNumber(final ComplexNum complexNumber) {
        if (Float.compare(complexNumber.getReal(), 0.f) == 0
                && Float.compare(complexNumber.getImaginary(), 0.f) == 0) {
            throw new ArithmeticException("Divider can't be zero");
        }
    }

    private static void checkNonNullNumber(final ComplexNum complexNumber) {
        if (complexNumber == null) {
            throw new IllegalArgumentException("Parameter can't be null");
        }
    }

    public static ComplexNum div(final ComplexNum complexNumA, final ComplexNum complexNumB) {
        checkNonNullNumber(complexNumA);
        checkNonNullNumber(complexNumB);
        checkNonZeroNumber(complexNumB);
        float factor = complexNumB.getReal() * complexNumB.getReal()
                + complexNumB.getImaginary() * complexNumB.getImaginary();
        ComplexNum representComplexNumB = new ComplexNum(complexNumB.getReal() / factor,
                -complexNumB.getImaginary() / factor);
        return mult(complexNumA, representComplexNumB);
    }

    public static float abs(final ComplexNum complexNumber) {
        checkNonNullNumber(complexNumber);
        return (float) Math.hypot(complexNumber.getReal(), complexNumber.getImaginary());
    }

    public static double getArgument(final ComplexNum complexNumber) {
        checkNonNullNumber(complexNumber);
        return Math.atan2((double) complexNumber.getImaginary(), (double) complexNumber.getReal());
    }
}

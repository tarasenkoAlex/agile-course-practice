package ru.unn.agile.color.model;

import static ru.unn.agile.color.model.Constants.*;

public final class CheckParameters {
    private CheckParameters() {

    }

    public static void checkParameters(ColorSpaces colorSpaces, final double[] params) {
        if (colorSpaces == ColorSpaces.HSV) {
            checkParametersHSV(params);
        } else if (colorSpaces == ColorSpaces.LAB) {
            checkParametersLAB(params);
        } else {
            checkParametersRGB(params);
        }
    }

    private static void checkParametersRGB(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] < 0 || params[i] > TWOHUNDREDFF) {
                throw new IllegalArgumentException();
            }
            if (params[i] % ONE != 0) {
                throw new NumberFormatException();
            }
        }
    }

    private static void checkParametersHSV(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        if (params[0] < 0 || params[0] > THS) {
            throw new IllegalArgumentException();
        }
        if (params[0] % ONE != 0) {
            throw new NumberFormatException();
        }
        if (params[1] < 0 || params[1] > ONEHUNDRED) {
                throw new IllegalArgumentException();
        }
        if (params[2] < 0 || params[2] > ONEHUNDRED) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkParametersLAB(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        if (params[0] < 0 || params[0] > ONEHUNDRED) {
            throw new IllegalArgumentException();
        }
        if (params[1] < -OHTE || params[1] > OHTE) {
            throw new IllegalArgumentException();
        }
        if (params[2] < -OHTE || params[2] > OHTE) {
            throw new IllegalArgumentException();
        }
    }
    static boolean checkNumberParameters(final double[] params) {
        if (params != null && params.length == THREEPZ) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

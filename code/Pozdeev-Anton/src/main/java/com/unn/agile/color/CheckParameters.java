package com.unn.agile.color;

import static com.unn.agile.color.Constants.*;

final class CheckParameters {
    private CheckParameters() {

    }
    static void checkParametersRGB(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] < 0 || params[i] > TWOHUNDREDFF) {
                throw new NumberFormatException();
            }
            if (params[i] % ONE != 0) {
                throw new NumberFormatException();
            }
        }
    }

    static void checkParametersHSV(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        if (params[0] < 0 || params[0] > THS) {
            throw new NumberFormatException();
        }
        if (params[0] % ONE != 0) {
            throw new NumberFormatException();
        }
        if (params[1] < 0 || params[1] > ONEHUNDRED) {
                throw new NumberFormatException();
        }
        if (params[2] < 0 || params[2] > ONEHUNDRED) {
            throw new NumberFormatException();
        }
    }

    static void checkParametersLAB(final double[] params) {
        if (!checkNumberParameters(params)) {
            return;
        }
        if (params[0] < 0 || params[0] > ONEHUNDRED) {
            throw new NumberFormatException();
        }
        if (params[1] < -OHTE || params[1] > OHTE) {
            throw new NumberFormatException();
        }
        if (params[2] < -OHTE || params[2] > OHTE) {
            throw new NumberFormatException();
        }
    }
    static boolean checkNumberParameters(final double[] params) {
        if (params != null && params.length != 0 && params.length == THREEPZ) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

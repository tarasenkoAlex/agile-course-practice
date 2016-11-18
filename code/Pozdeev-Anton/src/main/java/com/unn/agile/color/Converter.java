package com.unn.agile.color;

import static com.unn.agile.color.CheckParameters.*;

final class Converter {
    private Converter() {

    }

    static double[] rgbToHSV(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    static double[] rgbToLAB(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToLAB(rgb);
    }

    static double[] labToRGB(final double[] lab) {
        checkParametersLAB(lab);
        return ToRGBSpace.converterLABtoRGB(lab);
    }

    static double[] labToHSV(final double[] lab) {
        checkParametersLAB(lab);
        double[] rgb = ToRGBSpace.converterLABtoRGB(lab);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    static double[] hsvToRGB(final double[] hsv) {
        checkParametersHSV(hsv);
        return  ToRGBSpace.converterHSVtoRGB(hsv);
    }

    static double[] hsvToLAB(final double[] hsv) {
        checkParametersHSV(hsv);
        double[] rgb = ToRGBSpace.converterHSVtoRGB(hsv);
        return  FromRGBSpace.convertToLAB(rgb);
    }
}

package com.unn.agile.color;

import static com.unn.agile.color.CheckParameters.*;

public final class Converter {
    private Converter() {

    }

    public static double[] rgbToHSV(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    public static double[] rgbToLAB(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToLAB(rgb);
    }

    public static double[] labToRGB(final double[] lab) {
        checkParametersLAB(lab);
        return ToRGBSpace.converterLABtoRGB(lab);
    }

    public static double[] labToHSV(final double[] lab) {
        checkParametersLAB(lab);
        double[] rgb = ToRGBSpace.converterLABtoRGB(lab);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    public static double[] hsvToRGB(final double[] hsv) {
        checkParametersHSV(hsv);
        return  ToRGBSpace.converterHSVtoRGB(hsv);
    }

    public static double[] hsvToLAB(final double[] hsv) {
        checkParametersHSV(hsv);
        double[] rgb = ToRGBSpace.converterHSVtoRGB(hsv);
        return  FromRGBSpace.convertToLAB(rgb);
    }
}

package com.unn.agile.color;

import static com.unn.agile.color.CheckParameters.*;

final class Converter {
    private Converter() {

    }

    static double[] convert(final ColorSpaces fromColorSpace,
                            final ColorSpaces toColorSpace, final double[] color) {
        if (fromColorSpace == ColorSpaces.HSV && toColorSpace == ColorSpaces.RGB) {
            return hsvToRGB(color);
        } else if (fromColorSpace == ColorSpaces.HSV && toColorSpace == ColorSpaces.LAB) {
            return hsvToLAB(color);
        } else if (fromColorSpace == ColorSpaces.RGB && toColorSpace == ColorSpaces.LAB) {
                return rgbToLAB(color);
        } else if (fromColorSpace == ColorSpaces.RGB && toColorSpace == ColorSpaces.HSV) {
            return rgbToHSV(color);
        } else if (fromColorSpace == ColorSpaces.LAB && toColorSpace == ColorSpaces.RGB) {
            return labToRGB(color);
        } else if (fromColorSpace == ColorSpaces.LAB && toColorSpace == ColorSpaces.HSV) {
            return labToHSV(color);
        }
        throw new IllegalArgumentException("Color space not found");
    }

    private static double[] rgbToHSV(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    private static double[] rgbToLAB(final double[] rgb) {
        checkParametersRGB(rgb);
        return  FromRGBSpace.convertToLAB(rgb);
    }

    private static double[] labToRGB(final double[] lab) {
        checkParametersLAB(lab);
        return ToRGBSpace.converterLABtoRGB(lab);
    }

    private static double[] labToHSV(final double[] lab) {
        checkParametersLAB(lab);
        double[] rgb = ToRGBSpace.converterLABtoRGB(lab);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    private static double[] hsvToRGB(final double[] hsv) {
        checkParametersHSV(hsv);
        return  ToRGBSpace.converterHSVtoRGB(hsv);
    }

    private static double[] hsvToLAB(final double[] hsv) {
        checkParametersHSV(hsv);
        double[] rgb = ToRGBSpace.converterHSVtoRGB(hsv);
        return  FromRGBSpace.convertToLAB(rgb);
    }
}

package ru.unn.agile.ColorConverter.model;

public final class Converter {
    private Converter() {

    }

    public static double[] convert(final ColorSpaces fromColorSpace,
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
        } else if (fromColorSpace == toColorSpace) {
            return color;
        }
        throw new IllegalArgumentException("Color space not found");
    }

    private static double[] rgbToHSV(final double[] rgb) {
        CheckParameters.checkParameters(ColorSpaces.RGB, rgb);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    private static double[] rgbToLAB(final double[] rgb) {
        CheckParameters.checkParameters(ColorSpaces.RGB, rgb);
        return  FromRGBSpace.convertToLAB(rgb);
    }

    private static double[] labToRGB(final double[] lab) {
        CheckParameters.checkParameters(ColorSpaces.LAB, lab);
        return ToRGBSpace.converterLABtoRGB(lab);
    }

    private static double[] labToHSV(final double[] lab) {
        CheckParameters.checkParameters(ColorSpaces.LAB, lab);
        double[] rgb = ToRGBSpace.converterLABtoRGB(lab);
        return  FromRGBSpace.convertToHSV(rgb);
    }

    private static double[] hsvToRGB(final double[] hsv) {
        CheckParameters.checkParameters(ColorSpaces.HSV, hsv);
        return  ToRGBSpace.converterHSVtoRGB(hsv);
    }

    private static double[] hsvToLAB(final double[] hsv) {
        CheckParameters.checkParameters(ColorSpaces.HSV, hsv);
        double[] rgb = ToRGBSpace.converterHSVtoRGB(hsv);
        return  FromRGBSpace.convertToLAB(rgb);
    }
}

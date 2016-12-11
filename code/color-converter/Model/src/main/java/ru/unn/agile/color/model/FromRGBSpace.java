package ru.unn.agile.color.model;

final class FromRGBSpace {

    private FromRGBSpace() {

    }
    static double[] convertToLAB(final double[] rgb) {
        double[] xyzParameters = toXYZ(rgb);
        double x = xyzParameters[0] / Constants.REFX;
        double y = xyzParameters[1] / Constants.REFY;
        double z = xyzParameters[2] / Constants.REFZ;

        if (x > Constants.ZPZZEEFS) {
            x = Math.pow(x, (Constants.ONE / Constants.THREEPZ));
        } else {
            x = (Constants.SEVPSEV * x) + (Constants.SIXTEEN / Constants.ONEHUNDREED16);
        }

        if (y > Constants.ZPZZEEFS) {
            y = Math.pow(y, (Constants.ONE / Constants.THREEPZ));
        } else {
            y = (Constants.SEVPSEV * y) + (Constants.SIXTEEN / Constants.ONEHUNDREED16);
        }

        if (z > Constants.ZPZZEEFS) {
            z = Math.pow(z, (Constants.ONE / Constants.THREEPZ));
        } else {
            z = (Constants.SEVPSEV * z) + (Constants.SIXTEEN / Constants.ONEHUNDREED16);
        }

        double paramL = (Constants.ONEHUNDREED16 * y) - Constants.SIXTEEN;
        double paramA = Constants.FIVEHUNDRED * (x - y);
        double paramB = Constants.TWOHUNDRED * (y - z);

        paramL = Math.rint(Constants.ONETHOUSAND * paramL) / Constants.ONETHOUSAND;
        paramA = Math.rint(Constants.ONETHOUSAND * paramA) / Constants.ONETHOUSAND;
        paramB = Math.rint(Constants.ONETHOUSAND * paramB) / Constants.ONETHOUSAND;
        return new double[] {paramL, paramA, paramB};
    }

    static double[] convertToHSV(final double[] rgb) {
        double[] paramsOfHSV = rgb;
        double r = paramsOfHSV[0] / Constants.TWOHUNDREDFF;
        double g = paramsOfHSV[1] / Constants.TWOHUNDREDFF;
        double b = paramsOfHSV[2] / Constants.TWOHUNDREDFF;

        double max = Math.max(Math.max(r, g), b);
        double min = Math.min(Math.min(r, g), b);

        paramsOfHSV[2] = max * Constants.ONEHUNDRED;

        double delta = max - min;
        if (delta == 0) {
            paramsOfHSV[0] = 0;
            paramsOfHSV[1] = 0;
        } else {
            paramsOfHSV[1] = (delta / max) * Constants.ONEHUNDRED;
            double delR = (((max - r) / Constants.SIX) + (delta / Constants.TWO)) / delta;
            double delG = (((max - g) / Constants.SIX) + (delta / Constants.TWO)) / delta;
            double delB = (((max - b) / Constants.SIX) + (delta / Constants.TWO)) / delta;

            if (r == max) {
                paramsOfHSV[0] = delB - delG;
            } else if (g == max) {
                paramsOfHSV[0] = (Constants.ONE / Constants.THREEPZ) + delR - delB;
            } else if (b == max) {
                paramsOfHSV[0] = (Constants.TWO / Constants.THREEPZ) + delG - delR;
            }

            if (paramsOfHSV[0] < 0) {
                paramsOfHSV[0] += Constants.ONE;
            }
            if (paramsOfHSV[0] > Constants.ONE) {
                paramsOfHSV[0] -= Constants.ONE;
            }
        }
        paramsOfHSV[0] *= Constants.THS;
        paramsOfHSV[0] = Math.rint(paramsOfHSV[0]);
        for (int i = 0; i < paramsOfHSV.length; i++) {
            paramsOfHSV[i] = Math.rint(Constants.ONETHOUSAND * paramsOfHSV[i]) / Constants.ONETHOUSAND;
        }

        return paramsOfHSV;
    }

    private static double[] toXYZ(final double[] rgb) {
        double[] xyzParameters = rgb;
        double r = xyzParameters[0] / Constants.TWOHUNDREDFF;
        double g = xyzParameters[1] / Constants.TWOHUNDREDFF;
        double b = xyzParameters[2] / Constants.TWOHUNDREDFF;

        if (r > Constants.ZPZFZFF) {
            r = Math.pow(((r + Constants.ZEROPZERO5) / Constants.ONEPZFF), Constants.TPF);
        } else {
            r = r / Constants.TWELVEPNT;
        }

        if (g > Constants.ZPZFZFF) {
            g = Math.pow(((g + Constants.ZEROPZERO5) / Constants.ONEPZFF), Constants.TPF);
        } else {
            g = g / Constants.TWELVEPNT;
        }

        if (b > Constants.ZPZFZFF) {
            b = Math.pow(((b + Constants.ZEROPZERO5) / Constants.ONEPZFF), Constants.TPF);
        } else {
            b = b / Constants.TWELVEPNT;
        }

        r = r * Constants.ONEHUNDRED;
        g = g * Constants.ONEHUNDRED;
        b = b * Constants.ONEHUNDRED;

        xyzParameters[0] = r * Constants.ZPFOTF + g * Constants.ZPTFSS + b * Constants.ZPOEZF;
        xyzParameters[1] = r * Constants.ZPT1TS + g * Constants.ZPSOFT + b * Constants.ZPZSTT;
        xyzParameters[2] = r * Constants.ZPZONT + g * Constants.ZPOONT + b * Constants.ZPNFZF;

        for (int i = 0; i < xyzParameters.length; i++) {
            xyzParameters[i] = Math.rint(Constants.ONETHOUSAND * xyzParameters[i]) / Constants.ONETHOUSAND;
        }
        return xyzParameters;
    }
}

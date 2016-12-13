package ru.unn.agile.ColorConverter.model;

final class ToRGBSpace {
    private ToRGBSpace() {

    }
    static double[] converterLABtoRGB(final double[] lab) {
        double[] param = toXYZ(lab);
        double paramX = param[0] / Constants.ONEHUNDRED;
        double paramY = param[1] / Constants.ONEHUNDRED;
        double paramZ = param[2] / Constants.ONEHUNDRED;
        double paramR = paramX * Constants.THREEP24 + paramY * (-Constants.ONEPF) + paramZ * (-Constants.ZPFN);
        double paramG = paramX * (-Constants.ZPNSEN) + paramY * Constants.OPESFE + paramZ * Constants.ZPZFOF;
        double paramB = paramX * Constants.ZPZFFS + paramY * (-Constants.ZPTZFZ) + paramZ * Constants.OPZFSZ;
        paramR = paramR > Constants.ZPZZTOT ? (Constants.ONEPZFF * (Math.pow(paramR, (Constants.ONE / Constants.TPF))) - Constants.ZEROPZERO5)
                : Constants.TWELVEPNT * paramR;
        paramG = paramG > Constants.ZPZZTOT ? (Constants.ONEPZFF * (Math.pow(paramG, (Constants.ONE / Constants.TPF))) - Constants.ZEROPZERO5)
                : Constants.TWELVEPNT * paramG;
        paramB = paramB > Constants.ZPZZTOT ? (Constants.ONEPZFF * (Math.pow(paramB, (Constants.ONE / Constants.TPF))) - Constants.ZEROPZERO5)
                : Constants.TWELVEPNT * paramB;
        paramR = Math.round(paramR * Constants.TWOHUNDREDFF);
        paramG = Math.round(paramG * Constants.TWOHUNDREDFF);
        paramB = Math.round(paramB * Constants.TWOHUNDREDFF);
        return changeParam(new double[] {paramR, paramG, paramB});
    }

    static double[] converterHSVtoRGB(final double[] hsv) {
        double[] param = hsv;
        double paramR, paramG, paramB;

        if (param[1] == 0) {
            paramR = param[2] * Constants.TWOHUNDREDFF;
            paramG = param[2] * Constants.TWOHUNDREDFF;
            paramB = param[2] * Constants.TWOHUNDREDFF;
        } else {
            double paramH = param[0] / Constants.THS * Constants.SIX;
            param[1] /= Constants.ONEHUNDRED;
            param[2] /= Constants.ONEHUNDRED;
            if (paramH == Constants.SIX) {
                paramH = 0;
            }
            int tmpI = (int) paramH;
            double first = param[2] * (Constants.ONE - param[1]);
            double second = param[2] * (Constants.ONE - param[1] * (paramH - tmpI));
            double third = param[2] * (Constants.ONE - param[1] * (Constants.ONE - (paramH - tmpI)));

            switch (tmpI) {
                case 0:
                    paramR = param[2];
                    paramG = third;
                    paramB = first;
                    break;
                case 1:
                    paramR = second;
                    paramG = param[2];
                    paramB = first;
                    break;
                case 2:
                    paramR = first;
                    paramG = param[2];
                    paramB = third;
                    break;
                case (int) Constants.THREEPZ:
                    paramR = first;
                    paramG = second;
                    paramB = param[2];
                    break;
                case Constants.FOUR:
                    paramR = third;
                    paramG = first;
                    paramB = param[2];
                    break;
                default:
                    paramR = param[2];
                    paramG = first;
                    paramB = second;
                    break;
            }
            paramR *= Constants.TWOHUNDREDFF;
            paramG *= Constants.TWOHUNDREDFF;
            paramB *= Constants.TWOHUNDREDFF;
        }
        changeParam(new double[]{paramR, paramG, paramB});
        return new double[] {Math.round(paramR), Math.round(paramG), Math.round(paramB)};
    }

    private static double[] toXYZ(final double[] rgb) {
        double[] labParameters = rgb;
        double paramY = (labParameters[0] + Constants.SIXTEEN) / Constants.ONEHUNDREED16;
        double paramX = labParameters[1] / Constants.FIVEHUNDRED + paramY;
        double paramZ = paramY - (labParameters[2] / Constants.TWOHUNDRED);
        paramX = Math.pow(paramX, Constants.THREEPZ) > Constants.ZPZZEEFS ? Math.pow(paramX, Constants.THREEPZ)
                : (paramX - Constants.SIXTEEN / Constants.ONEHUNDREED16) / Constants.SEVPSEV;
        paramY = Math.pow(paramY, Constants.THREEPZ) > Constants.ZPZZEEFS ? Math.pow(paramY, Constants.THREEPZ)
                : (paramY - Constants.SIXTEEN / Constants.ONEHUNDREED16) / Constants.SEVPSEV;
        paramZ = Math.pow(paramZ, Constants.THREEPZ) > Constants.ZPZZEEFS ? Math.pow(paramZ, Constants.THREEPZ)
                : (paramZ - Constants.SIXTEEN / Constants.ONEHUNDREED16) / Constants.SEVPSEV;
        paramX = Math.rint(Constants.ONETHOUSAND * (paramX * Constants.REFX)) / Constants.ONETHOUSAND;
        paramY = Math.rint(Constants.ONETHOUSAND * (paramY * Constants.REFY)) / Constants.ONETHOUSAND;
        paramZ = Math.rint(Constants.ONETHOUSAND * (paramZ * Constants.REFZ)) / Constants.ONETHOUSAND;
        return new double[] {paramX, paramY, paramZ};
    }

    private static double[] changeParam(final double[] doubles) {
        for (int i = 0; i < doubles.length; i++) {
            if (doubles[i] < 0) {
                doubles[i] = 0;
            } else if (doubles[i] > Constants.TWOHUNDREDFF) {
                doubles[i] = Constants.TWOHUNDREDFF;
            }
        }
        return doubles;
    }
}

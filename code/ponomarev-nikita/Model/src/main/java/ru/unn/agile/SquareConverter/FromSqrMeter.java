package ru.unn.agile.SquareConverter;

public class FromSqrMeter {

    private static final double TOKILOMETERMULTIPLIER = 0.000001;
    private static final double TOHECTAREMULTIPLIER = 0.0001;
    private static final double TOARMULTIPLIER = 0.01;
    private static final double TOSQRSANTIMETERMULTIPLIER = 10000;
    private static final double TOSQRMILLIMETERMULTIPLIER = 1000000;
    private static final double TOSQRYARDMULTIPLIER = 1.196;
    private static final double TOSQRFOOTMULTIPLIER = 10.76;
    private static final double TOSQRINCHMULTIPLIER = 1550;
    private static final double TOSQRACREMULTIPLIER = 0.0002471;
    private static final double TOSQRMILEMULTIPLIER = 0.0000003861;

    public double toDistanationUnit(final double sourceUnit, final double distanationMultiplier)
    {
        if (sourceUnit < 0) {
            throw new IllegalArgumentException("Square must be positive number");
        }

        return distanationMultiplier * sourceUnit;
    }

    public double toSqrKilometer(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOKILOMETERMULTIPLIER);
    }

    public double toHectare(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOHECTAREMULTIPLIER);
    }

    public double toAr(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOARMULTIPLIER);
    }

    public double toSqrCentimeter(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRSANTIMETERMULTIPLIER);
    }

    public double toSqrMillimeter(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRMILLIMETERMULTIPLIER);
    }

    public double toSqrYard(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRYARDMULTIPLIER);
    }

    public double toSqrFoot(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRFOOTMULTIPLIER);
    }

    public double toSqrInch(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRINCHMULTIPLIER);
    }

    public double toSqrAcre(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRACREMULTIPLIER);
    }

    public double toSqrMile(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, TOSQRMILEMULTIPLIER);
    }

}

package ru.unn.agile.SquareConverter;

public class SquareConverter implements ISquareConverter {

    @Override
    public FromSqrMeter convertFromSqrMeter() {
        return new FromSqrMeter();
    }

}

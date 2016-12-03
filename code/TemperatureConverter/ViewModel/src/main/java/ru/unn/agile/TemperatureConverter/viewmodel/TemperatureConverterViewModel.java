package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.model.TemperatureConverter;
import ru.unn.agile.TemperatureConverter.model.TemperatureScale;

public class TemperatureConverterViewModel {
    private String firstValue;
    private String secondValue;
    private TemperatureScale firstScale;
    private TemperatureScale secondScale;
    private String warningLabelText;

    public TemperatureConverterViewModel() {
        firstValue = "";
        secondValue = "";
        warningLabelText = "";
        firstScale = TemperatureScale.CELSIUS;
        secondScale = TemperatureScale.CELSIUS;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setFirstValue(final String firstValue) {
        this.firstValue = firstValue;
        convertFirstToSecondValue();
    }

    public void setSecondValue(final String secondValue) {
        this.secondValue = secondValue;
        convertSecondToFirstValue();
    }

    private void convertFirstToSecondValue() {
        secondValue = convertValues(firstValue, firstScale, secondScale);
    }

    private void convertSecondToFirstValue() {
        firstValue = convertValues(secondValue, secondScale, firstScale);
    }

    private String convertValues(final String value,
                                 final TemperatureScale firstScale,
                                 final TemperatureScale secondScale) {
        try {
            double dFirstValue = Double.parseDouble(value);
            double dSecondValue =
                    TemperatureConverter.convert(dFirstValue, firstScale, secondScale);
            warningLabelText = "";

            return Double.toString(dSecondValue);
        } catch (Exception e) {
        }
        warningLabelText = "We wrote incorrect value of temperature!";

        return "";
    }

    public TemperatureScale getFirstScale() {
        return firstScale;
    }

    public TemperatureScale getSecondScale() {
        return secondScale;
    }

    public void setFirstScale(final String firstScale) {
        this.firstScale = setScaleValue(firstScale);
        convertSecondToFirstValue();
    }

    public void setSecondScale(final String secondScale) {
        this.secondScale = setScaleValue(secondScale);
        convertFirstToSecondValue();
    }

    private TemperatureScale setScaleValue(final String value) {
        try {
            if (!value.isEmpty()) {
                return TemperatureScale.valueOf(value);
            }
        } catch (Exception e) {
        }

        return TemperatureScale.CELSIUS;
    }

    public String getWarningLabelText() {
        return warningLabelText;
    }
}

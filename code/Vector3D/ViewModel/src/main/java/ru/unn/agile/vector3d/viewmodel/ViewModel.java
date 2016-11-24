package ru.unn.agile.vector3d.viewmodel;

import java.util.regex.Pattern;
import ru.unn.agile.vector3d.model.Vector3D;

public class ViewModel {
    private OperationTab activeTab;
    private String vectorText = "";
    private String dotProductOperandText = "";
    private String crossProductOperandText = "";
    private String normResultText = "";
    private String normalizationResultText = "";
    private String dotProductResultText = "";
    private String crossProductResultText = "";
    private Vector3D vector;
    private Vector3D dotProductOperand;
    private Vector3D crossProductOperand;
    private boolean buttonEnabled = false;

    public ViewModel() {
        activeTab = OperationTab.NORM;
    }

    public OperationTab getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(final OperationTab newActiveTab) {
        activeTab = newActiveTab;
    }

    public boolean isButtonEnabled() {
        return buttonEnabled;
    }

    public void enableButton() {
        buttonEnabled = true;
    }

    public void disableButton() {
        buttonEnabled = false;
    }

    public String getVectorText() {
        return vectorText;
    }

    public void setVectorText(final String text) {
        vectorText = text;
    }

    public String getDotProductOperandText() {
        return dotProductOperandText;
    }

    public void setDotProductOperandText(final String text) {
        dotProductOperandText = text;
    }

    public String getCrossProductOperandText() {
        return crossProductOperandText;
    }

    public void setCrossProductOperandText(final String text) {
        crossProductOperandText = text;
    }

    public String getNormResultText() {
        return normResultText;
    }

    public void setNormResultText(final String text) {
        normResultText = text;
    }

    public String getNormalizationResultText() {
        return normalizationResultText;
    }

    public void setNormalizationResultText(final String text) {
        normalizationResultText = text;
    }

    public String getDotProductResultText() {
        return dotProductResultText;
    }

    public void setDotProductResultText(final String text) {
        dotProductResultText = text;
    }

    public String getCrossProductResultText() {
        return crossProductResultText;
    }

    public void setCrossProductResultText(final String text) {
        crossProductResultText = text;
    }

    public boolean validate(final String text) {
        String coordPattern = "[\\+-]?[0-9]+(\\.[0-9]+)?";
        String vectorPattern = " *" + coordPattern + ", *"
                                    + coordPattern + ", *"
                                    + coordPattern + " *";
        String vectorInBracketsPattern = " *\\(" + vectorPattern + "\\) *";
        return Pattern.matches("^" + vectorPattern + "$", text)
            || Pattern.matches("^" + vectorInBracketsPattern + "$", text);
    }

    public Vector3D vectorFromString(final String text) {
        if (!validate(text)) {
            throw new IllegalArgumentException();
        }
        String[] coordTexts = text.replaceAll("[ \\+\\(\\)]+", "").split(",");
        double[] coords = new double[coordTexts.length];
        for (int i = 0; i < coordTexts.length; i++) {
            coords[i] = Double.parseDouble(coordTexts[i]);
        }
        return new Vector3D(coords);
    }


}

enum OperationTab {
    NORM,
    NORMALIZATION,
    DOTPRODUCT,
    CROSSPRODUCT
}

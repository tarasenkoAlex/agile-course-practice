package ru.unn.agile.VolumeCalculator.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class VolumeCalculatorViewModelTest {

    @Test
    public void isCalculateButtonDisabledByDefault() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        assertEquals(true, viewModel.getCalculateDisableProperty().get());
    }

    @Test
    public void isParam1VisibleByDefault() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        assertEquals(false, viewModel.getParam1VisibleProperty().getValue());
    }

    @Test
    public void isParam2VisibleByDefault() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void isResultEmptyByDefault() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        assertEquals("", viewModel.getResultVolumeProperty().get());
    }

    @Test
    public void isCorrectListBoxContent() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        ObjectProperty<ObservableList<EVolumeTypes>> items =
                viewModel.getVolumeTypeListItemsProperty();

        assertEquals(true, items.getValue().contains(EVolumeTypes.CONE));
        assertEquals(true, items.getValue().contains(EVolumeTypes.CUBE));
        assertEquals(true, items.getValue().contains(EVolumeTypes.CYLINDER));
        assertEquals(true, items.getValue().contains(EVolumeTypes.PYRAMID));
        assertEquals(true, items.getValue().contains(EVolumeTypes.SPHERE));
        assertEquals(true, items.getValue().contains(EVolumeTypes.TETRAHEDRON));
    }

    @Test
    public void whenGetSelectedValueItem() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(EVolumeTypes.SPHERE, viewModel.getSelectedVolumeItem());
    }

    @Test
    public void whenSphereChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenSphereChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenCubeChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenCubeChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenConeChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenConeChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenCylinderChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenCylinderChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenPyramidChosenParameter1Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenPyramidChosenParameter2Visible() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals("Edge", viewModel.getParam1Name().get());
    }

    @Test
    public void whenCubeChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals("Edge", viewModel.getParam1Name().get());
    }

    @Test
    public void whenSphereChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenPyramidChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals("Area", viewModel.getParam1Name().get());
    }

    @Test
    public void whenPyramidChosenParameter2Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenCylinderChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenCylinderChosenParameter2Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenConeChosenParameter1Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenConeChosenParameter2Name() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenSelectedVolumeChangesIsParamsValuesEmpty() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(null, viewModel.getParam1ValueProperty().getValue());
        assertEquals(null, viewModel.getParam2ValueProperty().getValue());
    }

    @Test
    public void whenSelectedVolumeChangesIsResultValueEmpty() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setResultVolumeProperty("123");
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);

        assertEquals("", viewModel.getResultVolumeProperty().getValue());
    }

    @Test
    public void isValidationMsgEmptyByDefault() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1IsNotValid() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("asdf");
        String validationMsg = viewModel.getParam1Name().getValue() + " is not valid";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1ChangesOnEmptyIsValidationMsgEmpty() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("");
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1IsNegative() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("-123");
        String validationMsg = viewModel.getParam1Name().getValue() + " must be positive";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2IsNotValid() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam2ValueProperty("asdf");
        String validationMsg = viewModel.getParam2Name().getValue() + " is not valid";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2ChangesOnEmptyIsValidationMsgEmpty() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam2ValueProperty("");
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2IsNegative() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam2ValueProperty("-123");
        String validationMsg = viewModel.getParam2Name().getValue() + " must be positive";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParamsAreValidIsCalculateButtonDisable() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("123");
        viewModel.setParam2ValueProperty("456");

        assertEquals(false, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreNotValidIsCalculateButtonDisable() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("123");
        viewModel.setParam1ValueProperty("hkj");

        assertEquals(true, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreEmptyIsCalculateButtonDisable() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("");
        viewModel.setParam2ValueProperty("");

        assertEquals(true, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreEmptyIsValidationMsgEmpty() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setParam1ValueProperty("");
        viewModel.setParam2ValueProperty("");

        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenOneValidParameterIsCalculateDisable() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        viewModel.setParam1ValueProperty("123");

        assertEquals(false, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void isResultEmptyAfterCalculate() {
        VolumeCalculatorViewModel viewModel = new VolumeCalculatorViewModel();
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        viewModel.setParam1ValueProperty("12");
        viewModel.setResultVolumeProperty("78");
        viewModel.calculate();

        assertNotEquals("", viewModel.getResultVolumeProperty().getValue());
    }

}

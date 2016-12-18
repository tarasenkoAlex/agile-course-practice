package ru.unn.agile.VolumeCalculator.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VolumeCalculatorViewModelTest {

    private VolumeCalculatorViewModel viewModel;
    public void setExternalViewModel(final VolumeCalculatorViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new VolumeCalculatorViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isCalculateButtonDisabledByDefault() {
        assertEquals(true, viewModel.getCalculateDisableProperty().get());
    }

    @Test
    public void isParam1VisibleByDefault() {
        assertEquals(false, viewModel.getParam1VisibleProperty().getValue());
    }

    @Test
    public void isParam2VisibleByDefault() {
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void isResultEmptyByDefault() {
        assertEquals("", viewModel.getResultVolumeProperty().get());
    }

    @Test
    public void isCorrectListBoxContent() {
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
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(EVolumeTypes.SPHERE, viewModel.getSelectedVolumeItem());
    }

    @Test
    public void whenSphereChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenSphereChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenCubeChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenCubeChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenConeChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenConeChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenCylinderChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenCylinderChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals(false, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenPyramidChosenParameter1Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals(true, viewModel.getParam1VisibleProperty().get());
    }

    @Test
    public void whenPyramidChosenParameter2Visible() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals(true, viewModel.getParam2VisibleProperty().get());
    }

    @Test
    public void whenTetrahedronChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.TETRAHEDRON);
        assertEquals("Edge", viewModel.getParam1Name().get());
    }

    @Test
    public void whenCubeChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CUBE);
        assertEquals("Edge", viewModel.getParam1Name().get());
    }

    @Test
    public void whenSphereChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenPyramidChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals("Area", viewModel.getParam1Name().get());
    }

    @Test
    public void whenPyramidChosenParameter2Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenCylinderChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenCylinderChosenParameter2Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenConeChosenParameter1Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals("Radius", viewModel.getParam1Name().get());
    }

    @Test
    public void whenConeChosenParameter2Name() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CONE);
        assertEquals("Height", viewModel.getParam2Name().get());
    }

    @Test
    public void whenSelectedVolumeChangesIsParamsValuesEmpty() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        assertEquals(null, viewModel.getParam1ValueProperty().getValue());
        assertEquals(null, viewModel.getParam2ValueProperty().getValue());
    }

    @Test
    public void whenSelectedVolumeChangesIsResultValueEmpty() {
        viewModel.setResultVolumeProperty("123");
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);

        assertEquals("", viewModel.getResultVolumeProperty().getValue());
    }

    @Test
    public void isValidationMsgEmptyByDefault() {
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1IsNotValid() {
        viewModel.setParam1ValueProperty("asdf");
        String validationMsg = viewModel.getParam1Name().getValue() + " is not valid";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1ChangesOnEmptyIsValidationMsgEmpty() {
        viewModel.setParam1ValueProperty("");
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam1IsNegative() {
        viewModel.setParam1ValueProperty("-123");
        String validationMsg = viewModel.getParam1Name().getValue() + " must be positive";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2IsNotValid() {
        viewModel.setParam2ValueProperty("asdf");
        String validationMsg = viewModel.getParam2Name().getValue() + " is not valid";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2ChangesOnEmptyIsValidationMsgEmpty() {
        viewModel.setParam2ValueProperty("");
        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParam2IsNegative() {
        viewModel.setParam2ValueProperty("-123");
        String validationMsg = viewModel.getParam2Name().getValue() + " must be positive";
        assertEquals(validationMsg, viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenParamsAreValidIsCalculateButtonDisable() {
        viewModel.setParam1ValueProperty("123");
        viewModel.setParam2ValueProperty("456");

        assertEquals(false, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreNotValidIsCalculateButtonDisable() {
        viewModel.setParam1ValueProperty("123");
        viewModel.setParam1ValueProperty("hkj");

        assertEquals(true, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreEmptyIsCalculateButtonDisable() {
        viewModel.setParam1ValueProperty("");
        viewModel.setParam2ValueProperty("");

        assertEquals(true, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void whenParamsAreEmptyIsValidationMsgEmpty() {
        viewModel.setParam1ValueProperty("");
        viewModel.setParam2ValueProperty("");

        assertEquals("", viewModel.getValidationMsgProperty().getValue());
    }

    @Test
    public void whenOneValidParameterIsCalculateDisable() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.SPHERE);
        viewModel.setParam1ValueProperty("123");

        assertEquals(false, viewModel.getCalculateDisableProperty().getValue());
    }

    @Test
    public void isResultEmptyAfterCalculate() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.CYLINDER);
        viewModel.setParam1ValueProperty("12");
        viewModel.setResultVolumeProperty("78");
        viewModel.calculate();

        assertNotEquals("", viewModel.getResultVolumeProperty().getValue());
    }
}

package ru.unn.agile.VolumeCalculator.viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VolumeCalculatorViewModelTest {
    private VolumeCalculatorViewModel viewModel;
    private double delta = 0.01;

    public void setExternalViewModel(final VolumeCalculatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new VolumeCalculatorViewModel(new FakeVolumeCalculatorLogger());
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

    @Test
    public void getVolumeCube() {
        double value = EVolumeTypes.CUBE.getVolume(3.0);
        assertEquals(27.0, value, delta);
    }

    @Test
    public void getVolumeCone() {
        double value = EVolumeTypes.CONE.getVolume(2.0, 3.0);
        assertEquals(12.56, value, delta);
    }

    @Test
    public void getVolumeCylinder() {
        double value = EVolumeTypes.CYLINDER.getVolume(2.0, 3.0);
        assertEquals(37.69, value, delta);
    }

    @Test
    public void getVolumePyramid() {
        double value = EVolumeTypes.PYRAMID.getVolume(4.0, 3.0);
        assertEquals(3.99, value, delta);
    }

    @Test
    public void getVolumeTetrahedron() {
        double value = EVolumeTypes.TETRAHEDRON.getVolume(2.0);
        assertEquals(0.94, value, delta);
    }

    @Test
    public void getVolumeSphere() {
        double value = EVolumeTypes.SPHERE.getVolume(1.0);
        assertEquals(4.18, value, delta);
    }

    @Test
    public void emptyLogInTheBeginning() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void whenLogMessageIsEmpty() {
        setInputData();
        assertEquals(null, viewModel.getLogs());
    }

    @Test
    public void whenLogMessageIsNotEmpty() {
        setInputData();
        viewModel.calculate();
        assertFalse(viewModel.getLogs().isEmpty());
    }

    @Test
    public void whenLogPropertyMessageIsEmpty() {
        setInputData();
        assertEquals(null, viewModel.logsProperty().getValue());
    }

    @Test
    public void whenLogPropertyMessageIsNotEmpty() {
        setInputData();
        viewModel.calculate();
        assertFalse(viewModel.logsProperty().getValue().isEmpty());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullVCLogger() {
        try {
            new VolumeCalculatorViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logContainsCalculateMessageAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void canMessageWhenOperationWasChangedInLog() {
        setInputData();
        viewModel.onVolumeTypeChanged(EVolumeTypes.SPHERE, EVolumeTypes.CONE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_WAS_CHANGED + "Cone.*"));
    }

    @Test
    public void whenCorrectArgumentsLogged() {
        setInputData();
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.getParam1ValueProperty().get() + "; "
                + viewModel.getParam2ValueProperty().get() + "\\]"));
    }

    @Test
    public void checkOperationIsNotLoggedIfNotChanged() {
        viewModel.onVolumeTypeChanged(EVolumeTypes.CONE, EVolumeTypes.CYLINDER);
        viewModel.onVolumeTypeChanged(EVolumeTypes.CYLINDER, EVolumeTypes.CYLINDER);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();
        viewModel.calculate();
        viewModel.calculate();
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void checkArgumentsInLogMessage() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": param1 = " + viewModel.getParam1ValueProperty().get()
                + "; param2 = " + viewModel.getParam2ValueProperty().get() + ".*"));
    }

    @Test
    public void checkOperationTypeInTheLog() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Pyramid.*"));
    }

    @Test
    public void logMessageWithInputArgumentsAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getParam1ValueProperty().get()
                + ".*" + viewModel.getParam2ValueProperty().get() + ".*"));
    }

    @Test
    public void getSelectedItemProperty() {
        setInputData();
        assertEquals("Pyramid", viewModel.getSelectedItemProperty().get().toString());
    }

    public void setInputData() {
        viewModel.setSelectedVolumeItem(EVolumeTypes.PYRAMID);
        viewModel.setParam1ValueProperty("2.0");
        viewModel.setParam2ValueProperty("3.0");
    }
}

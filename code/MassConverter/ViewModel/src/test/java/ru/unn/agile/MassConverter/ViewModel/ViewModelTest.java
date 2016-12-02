package ru.unn.agile.MassConverter.ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.unn.agile.MassConverter.Model.MassConverter.*;
import static ru.unn.agile.MassConverter.ViewModel.ViewModel.Status.*;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void start() {
        viewModel = new ViewModel();
    }

    @After
    public void finish() {
        viewModel = null;
    }

    @Test
    public void setDefaultValues() {
        assertEquals("", viewModel.inputProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
        assertEquals(ConversionSystem.GRAM.toString(),
                viewModel.systemToConvertProperty().get().toString());
    }

    @Test
    public void invalidInput() {
        viewModel.inputProperty().set("a");
        assertEquals(WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void emptyInput() {
        viewModel.inputProperty().set("");
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void validInput() {
        viewModel.inputProperty().set("1");
        assertEquals(SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convert1kgTo1000Gram() {
        viewModel.inputProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
    }

    @Test
    public void convert1kgToTonne() {
        viewModel.systemToConvertProperty().set(ConversionSystem.TONNE);
        viewModel.inputProperty().set("1");
        assertEquals("0.001", viewModel.resultProperty().get());
    }

    @Test
    public void convert1kgToTonneAfterChangeSystem() {
        viewModel.inputProperty().set("1");
        viewModel.systemToConvertProperty().set(ConversionSystem.TONNE);
        assertEquals("0.001", viewModel.resultProperty().get());
    }

    @Test
    public void convertEmptyInputAfterChangeSystem() {
        viewModel.systemToConvertProperty().set(ConversionSystem.TONNE);
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertInvalidInputAfterChangeSystem() {
        viewModel.inputProperty().set("a");
        viewModel.systemToConvertProperty().set(ConversionSystem.TONNE);
        assertEquals(WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void testGetStringResult() {
        viewModel.inputProperty().set("1");
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void testGetStringStatus() {
        assertEquals(WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void testGetConversionSystems() {
        ObservableList<ConversionSystem> observableList
                = FXCollections.observableArrayList(ConversionSystem.values());
        assertEquals(observableList, viewModel.getConversionSystems());
    }

    @Test
    public void emptyResultWhenInvalidInput() {
        viewModel.inputProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
        viewModel.inputProperty().set("a");
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void emptyResultWhenEmptyInput() {
        viewModel.inputProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
        viewModel.inputProperty().set("");
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void convert1CentnerTo100000Gram() {
        viewModel.inputProperty().set("1");
        viewModel.systemFromConvertProperty().set(ConversionSystem.CENTNER);
        assertEquals("100000.0", viewModel.resultProperty().get());
    }

    @Test
    public void convertNegative() {
        viewModel.inputProperty().set("-1");
        assertEquals(WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }
}

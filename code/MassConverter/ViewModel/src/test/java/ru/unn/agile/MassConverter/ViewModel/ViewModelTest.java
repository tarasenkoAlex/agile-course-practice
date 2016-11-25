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
        assertEquals("", viewModel.kilogramProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
        assertEquals(SystemToConvert.GRAM.toString(),
                viewModel.systemToConvertProperty().get().toString());
    }

    @Test
    public void invalidInput() {
        viewModel.kilogramProperty().set("a");
        assertEquals(WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void emptyInput() {
        viewModel.kilogramProperty().set("");
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void validInput() {
        viewModel.kilogramProperty().set("1");
        assertEquals(SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convert1kgTo1000Gram() {
        viewModel.kilogramProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
    }

    @Test
    public void convert1kgToTonne() {
        viewModel.systemToConvertProperty().set(SystemToConvert.TONNE);
        viewModel.kilogramProperty().set("1");
        assertEquals("0.001", viewModel.resultProperty().get());
    }

    @Test
    public void convert1kgToTonneAfterChangeSystem() {
        viewModel.kilogramProperty().set("1");
        viewModel.systemToConvertProperty().set(SystemToConvert.TONNE);
        assertEquals("0.001", viewModel.resultProperty().get());
    }

    @Test
    public void convertEmptyInputAfterChangeSystem() {
        viewModel.systemToConvertProperty().set(SystemToConvert.TONNE);
        assertEquals(WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertInvalidInputAfterChangeSystem() {
        viewModel.kilogramProperty().set("a");
        viewModel.systemToConvertProperty().set(SystemToConvert.TONNE);
        assertEquals(WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void testGetStringResult() {
        viewModel.kilogramProperty().set("1");
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void testGetStringStatus() {
        assertEquals(WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void testGetSystemsToConvert() {
        ObservableList<SystemToConvert> observableList
                = FXCollections.observableArrayList(SystemToConvert.values());
        assertEquals(observableList, viewModel.getSystemsToConvert());
    }

    @Test
    public void emptyResultWhenInvalidInput() {
        viewModel.kilogramProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
        viewModel.kilogramProperty().set("a");
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void emptyResultWhenEmptyInput() {
        viewModel.kilogramProperty().set("1");
        assertEquals("1000.0", viewModel.resultProperty().get());
        viewModel.kilogramProperty().set("");
        assertEquals("", viewModel.resultProperty().get());
    }
}

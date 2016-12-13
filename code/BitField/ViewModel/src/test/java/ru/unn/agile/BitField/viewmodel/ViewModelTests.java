package ru.unn.agile.BitField.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void byDefault_InputAButtonIsDisabled() {
        assertFalse(viewModel.isInputAButtonEnabled(0));
    }

    @Test
    public void byDefault_InputBButtonIsDisabled() {
        assertFalse(viewModel.isInputAButtonEnabled(1));
    }

    @Test
    public void whenEnter_InputAButtonIEnabled() {
        viewModel.setBitField("1", 0);

        assertTrue(viewModel.isInputAButtonEnabled(0));
    }

    @Test
    public void whenEnter_InputBButtonIEnabled() {
        viewModel.setBitField("1", 1);

        assertTrue(viewModel.isInputAButtonEnabled(1));
    }
}

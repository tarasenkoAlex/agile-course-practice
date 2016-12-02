package ru.unn.agile.Statistics.viewmodel;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static ru.unn.agile.Statistics.viewmodel.Operation.IM;
import static ru.unn.agile.Statistics.viewmodel.ViewModel.DEFAULT_DELTA;
import static ru.unn.agile.Statistics.viewmodel.ViewModel.DEFAULT_OPERATION;
import static ru.unn.agile.Statistics.viewmodel.ViewModel.Status.READY;


public class GoodInputTests extends Core {
    @After
    public void checkPersistentParameters() {
        assertStatusIs(READY);
        assertEquals("", vm().getResult());
        assertTrue(vm().isCalculateButtonEnabled());
    }

    @Test
    public void whenFieldsAreFilled() {
        fillInputFields();

        assertArraysAreFromTest();
        assertEquals(DEFAULT_DELTA, vm().getDelta());
        assertOperationIs(DEFAULT_OPERATION);
    }

    @Test
    public void whenFieldsAreFilledAndDeltaSet() {
        fillInputFields();
        vm().setDelta("1.0");

        assertArraysAreFromTest();
        assertEquals("1.0", vm().getDelta());
        assertOperationIs(DEFAULT_OPERATION);
    }

    @Test
    public void whenIMAndFieldsAreFilledAndMomentOrderSet() {
        fillInputFields();
        vm().setOperation(IM);
        vm().setMomentOrder("1");

        assertEquals("1", vm().getMomentOrder());
        assertOperationIs(IM);
        assertEquals(DEFAULT_DELTA, vm().getDelta());
    }
}

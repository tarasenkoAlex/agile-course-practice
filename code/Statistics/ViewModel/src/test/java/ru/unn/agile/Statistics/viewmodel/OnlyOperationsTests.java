package ru.unn.agile.Statistics.viewmodel;

import org.junit.Test;
import static org.junit.Assert.*;

import static ru.unn.agile.Statistics.viewmodel.Operation.EV;
import static ru.unn.agile.Statistics.viewmodel.Operation.VAR;
import static ru.unn.agile.Statistics.viewmodel.Operation.IM;
import static ru.unn.agile.Statistics.viewmodel.Operation.values;


public class OnlyOperationsTests {
    @Test
    public void canGetOperationsName() {
        for (Operation operation : Operation.values()) {
            assertNotNull(operation.toString());
        }
    }

    @Test
    public void canGetNumberOfOperations() {
        int nOperations = values().length;
        assertEquals(3, nOperations);
    }

    @Test
    public void canGetListOfOperations() {
        Operation[] operations = values();
        Operation[] currentOperations = new Operation[] {EV, VAR, IM};
        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertEquals(EV, EV);
        assertNotEquals(EV, VAR);
    }


    @Test
    public void expectedValue() {
        assertEquals("Expected value", EV.toString());
        assertIsComputable(EV);
    }

    @Test(expected = ClassCastException.class)
    public void expectedValueIsNotComputableWithOrder() {
        assertNotNull(EV.toComputableWithOrder());
    }


    @Test
    public void variance() {
        assertEquals("Variance", VAR.toString());
        assertIsComputable(VAR);
    }

    @Test(expected = ClassCastException.class)
    public void varianceIsNotComputableWithOrder() {
        assertNotNull(VAR.toComputableWithOrder());
    }


    @Test
    public void initialMoment() {
        assertEquals("Initial moment", IM.toString());
        assertIsComputableWithOrder(IM);
    }

    @Test(expected = ClassCastException.class)
    public void initialMomentIsNotComputable() {
        assertNotNull(IM.toComputable());
    }



    private static void assertIsComputable(final Operation operation) {
        assertNotNull(operation.toComputable());
        assertTrue(operation.is(Computable.class));
        assertFalse(operation.is(ComputableWithMomentOrder.class));
    }

    private static void assertIsComputableWithOrder(final Operation operation) {
        assertNotNull(operation.toComputableWithOrder());
        assertTrue(operation.is(ComputableWithMomentOrder.class));
        assertFalse(operation.is(Computable.class));
    }
}

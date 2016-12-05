package ru.unn.agile.NewtonRoots.Model;

import org.junit.Test;

import static org.junit.Assert.fail;

public class MathFunctionTests {

    @Test(expected = Exception.class)
    public void createFunctionFromInvalidExpression() throws Exception {
        MathFunction func = new MathFunction("-z");
    }
    @Test
    public void evaluateValidFunction() {
        try {
            MathFunction func = new MathFunction("x^2");
        } catch (Exception e)  {
            fail(e.getMessage());
        }
    }
}

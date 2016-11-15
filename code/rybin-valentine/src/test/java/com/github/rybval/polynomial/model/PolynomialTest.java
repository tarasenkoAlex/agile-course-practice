package com.github.rybval.polynomial.model;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PolynomialTest {
    @Test
    public void canCreatePolynomial() {
        Polynomial polynomial = new Polynomial();
        assertNotNull(polynomial);
    }

    @Test
    public void canCreatePolynomialWithListOfMonomials() {
        List<Monomial> monomialsList = new ArrayList<Monomial>();
        monomialsList.add(new Monomial(5, 5.4));
        monomialsList.add(new Monomial(3, 2.1));

        Polynomial polynomial = new Polynomial(monomialsList);

        assertNotNull(polynomial);
    }

    @Test
    public void canCreatePolynomialWithArrayOfMonomials() {
        Monomial[] monomialsArray = {new Monomial(5, 5.4),
                                     new Monomial(3, 2.1),
                                     new Monomial(3, -5)};

        Polynomial polynomial = new Polynomial(monomialsArray);

        assertNotNull(polynomial);
    }

    @Test
    public void canCreatePolynomialFromString() {
        Polynomial polynomial = Polynomial.fromString("32 +5.4*x - 21*x^2+x^3");
        assertNotNull(polynomial);
    }

    @Test
    public void canPresentPolynomialAsString() {
        String polynomialString = "50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10";
        Polynomial polynomial = Polynomial.fromString(polynomialString);

        assertEquals(polynomialString, polynomial.toString());
    }

    @Test
    public void canPresentPositivePolynomialAsString() {
        String polynomialString = "+50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10";
        Polynomial polynomial = Polynomial.fromString(polynomialString);

        assertEquals("50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10",
                     polynomial.toString());
    }

    @Test
    public void canPresentNegativePolynomialAsString() {
        String polynomialString = "-50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10";
        Polynomial polynomial = Polynomial.fromString(polynomialString);

        assertEquals(polynomialString, polynomial.toString());
    }

    @Test
    public void isSamePolynomialsCompareCorrect() {
        String polynomialString = "-50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10";
        Polynomial polynomial1 = Polynomial.fromString(polynomialString);
        Polynomial polynomial2 = Polynomial.fromString(polynomialString);

        assertTrue(polynomial1.equals(polynomial2));
    }

    @Test
    public void isDifferentPolynomialsCompareCorrect() {
        Polynomial polynomial1 = Polynomial.fromString("x + 5*x^2");
        Polynomial polynomial2 = Polynomial.fromString("20 - 1.2*x^5 + x^10");

        assertFalse(polynomial1.equals(polynomial2));
    }

    @Test
    public void isCompareWithNullCorrect() {
        Polynomial polynomial = Polynomial.fromString("x + 5*x^2");
        assertFalse(polynomial.equals(null));
    }

    @Test
    public void isSamePolynomialsHashCodesEqual() {
        String polynomialString = "-50.0*x^0 + 1.0*x^1 - 1.0*x^2 + 5.78*x^10";
        Polynomial polynomial1 = Polynomial.fromString(polynomialString);
        Polynomial polynomial2 = Polynomial.fromString(polynomialString);

        assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
    }

    @Test
    public void isDifferentPolynomialsHashCodesDifferent() {
        Polynomial polynomial1 = Polynomial.fromString("x + 5*x^2");
        Polynomial polynomial2 = Polynomial.fromString("20 - 1.2*x^5 + x^10");

        assertNotEquals(polynomial1.hashCode(), polynomial2.hashCode());
    }

    @Test
    public void isReductionCorrect() {
        Polynomial polynomial1 = Polynomial.fromString("25*x^5 + 6*x^5 - 4.2*x^5");
        Polynomial polynomial2 = Polynomial.fromString("26.8*x^5");
        assertEquals(polynomial1, polynomial2);
    }

    @Test
    public void isAdditionCorrect() {
        Polynomial polynomial1 = Polynomial.fromString("1 + x + 5*x^2");
        Polynomial polynomial2 = Polynomial.fromString("x - 3*x^2 - 5.4*x^3");

        Polynomial summ = polynomial1.add(polynomial2);

        assertEquals(Polynomial.fromString("1 + 2*x + 2*x^2 - 5.4*x^3"), summ);
    }

    @Test
    public void isSubtractionCorrect() {
        Polynomial minuend = Polynomial.fromString("1 + x + 5*x^2");
        Polynomial subtrahend = Polynomial.fromString("x - 3*x^2 - 5.4*x^3");

        Polynomial diff = minuend.subtract(subtrahend);

        assertEquals(Polynomial.fromString("1 + 8*x^2 + 5.4*x^3"), diff);
    }

    @Test
    public void isMultiplicationByMonomialCorrect() {
        Polynomial multiplicand = Polynomial.fromString("1 + x - 5*x^2");
        Monomial multiplier = Monomial.fromString("2.5*x^3");

        Polynomial product = multiplicand.multiply(multiplier);

        assertEquals(Polynomial.fromString("2.5*x^3 + 2.5*x^4 - 12.5*x^5"),
                     product);
    }

    @Test
    public void isMultiplicationByPolynomialCorrect() {
        Polynomial multiplicand = Polynomial.fromString("1 + x + 5*x^2");
        Polynomial multiplier = Polynomial.fromString("x - 3*x^2 - 5*x^3");

        Polynomial product = multiplicand.multiply(multiplier);

        assertEquals(Polynomial.fromString("x - 2*x^2 - 3*x^3 - 20*x^4 - 25*x^5"),
                                           product);
    }

    @Test
    public void areZeroMonomialsNotStored() {
        Polynomial polynomial = Polynomial.fromString("0 + x - 0*x^2 + 5.4*x^3");
        Polynomial cleanPolynomial =  Polynomial.fromString("x + 5.4*x^3");

        assertEquals(cleanPolynomial, polynomial);
    }

    @Test
    public void canNegatePolynomial() {
        Polynomial positive = Polynomial.fromString("+x - 3*x^2 + 5*x^3");
        Polynomial negative = Polynomial.fromString("-x + 3*x^2 - 5*x^3");

        assertEquals(negative, positive.negate());
    }

    @Test
    public void canGetPolynomialDegree() {
        Polynomial polynomial = Polynomial.fromString("x - 3*x^2 - 5*x^3");
        assertEquals(3, polynomial.getDegree());
    }

    @Test
    public void isExponentiationCorrect() {
        Polynomial polynomial = Polynomial.fromString("x - 3*x^2 - 5*x^3");

        Polynomial exponented = polynomial.exponentiate(2);

        assertEquals(Polynomial.fromString("1*x^2 - 6*x^3 - 1*x^4 + 30*x^5 + 25*x^6"),
                     exponented);
    }

    @Test
    public void canEvaluatePolynomial() {
        Polynomial polynomial = Polynomial.fromString("x - 3*x^2 + 5*x^3");
        int variable = 2;

        double value = polynomial.eval(2);

        assertEquals(30, value, Double.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantDivideByZeroPolynomial() {
        Polynomial divident = Polynomial.fromString("x - 3*x^2 - 5*x^3");
        Polynomial zeroPolynomial = Polynomial.fromString("0");

        Polynomial quotient = divident.div(zeroPolynomial);
    }

    @Test
    public void isDivisionByPolynomialWithUpperDegreeCorrect() {
        Polynomial divident = Polynomial.fromString("1 + x - 5*x^2");
        Polynomial divider = Polynomial.fromString("x - 3*x^2 - 5*x^3");

        Polynomial quotient = divident.div(divider);

        assertEquals(Polynomial.fromString("0"), quotient);
    }

    @Test
    public void isDivisionByPolynomialWithSameDegreeCorrect() {
        Polynomial divident = Polynomial.fromString("4*x^2 + 2*x - 2");
        Polynomial divider = Polynomial.fromString("2*x^2 + x - 1");

        Polynomial quotient = divident.div(divider);

        assertEquals(Polynomial.fromString("2"), quotient);
    }

    @Test
    public void isDivisionByPolynomialWithLowerDegreeCorrect() {
        Polynomial divident = Polynomial.fromString("4*x^4 + 4*x^3 - 3*x^2 - 2*x + 1");
        Polynomial divider = Polynomial.fromString("2*x^2 + x - 1");

        Polynomial quotient = divident.div(divider);

        assertEquals(Polynomial.fromString("2*x^2 + x - 1"), quotient);
    }

    @Test
    public void canPresentZeroPolynomialAsString() {
        assertEquals("0", Polynomial.fromString("0").toString());
    }

    @Test
    public void canCreatePolynomialWithMonomialsAsArgs() {
        Polynomial polynomial = new Polynomial(new Monomial(2, 5), new Monomial(3, 2));

        assertEquals(Polynomial.fromString("5*x^2 + 2*x^3"), polynomial);
    }

    @Test
    public void canAddMonomial() {
        Polynomial polynomial = Polynomial.fromString("2*x^2 + x - 1");
        Monomial monomial = new Monomial(1, 2.5);

        Polynomial summ = polynomial.add(monomial);

        assertEquals(Polynomial.fromString("2*x^2 + 3.5*x - 1"), summ);
    }

    @Test
    public void canSubtractMonomial() {
        Polynomial polynomial = Polynomial.fromString("2*x^2 + x - 1");
        Monomial monomial = new Monomial(1, 2.5);

        Polynomial diff = polynomial.subtract(monomial);

        assertEquals(Polynomial.fromString("2*x^2 - 1.5*x - 1"), diff);
    }

    @Test
    public void canClonePolynomial() {
        Polynomial original = Polynomial.fromString("2*x^2 + x - 1");

        Polynomial clone = original.clone();

        assertEquals(original, clone);
    }

    @Test
    public void canGetMonomialWithHighestDegree() {
        Polynomial polynomial = Polynomial.fromString("3*x^2 + x - 1");

        Monomial monomial = polynomial.getMonomialWithMaxDegree();

        assertEquals(new Monomial(2, 3), monomial);
    }

    @Test
    public void canGetDegreeOfZeroPolynomial() {
        Polynomial polynomial = new Polynomial();
        assertEquals(0, polynomial.getDegree());
    }
}

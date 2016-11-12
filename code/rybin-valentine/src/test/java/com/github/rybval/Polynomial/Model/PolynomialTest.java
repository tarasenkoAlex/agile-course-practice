package com.github.rybval.Polynomial.Model;

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
}

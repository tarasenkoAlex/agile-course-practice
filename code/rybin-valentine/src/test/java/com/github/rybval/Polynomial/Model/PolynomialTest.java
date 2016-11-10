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
                                     new Monomial(3, 2.1)};

        Polynomial polynomial = new Polynomial(monomialsArray);

        assertNotNull(polynomial);
    }
}

package com.github.rybval.Polynomial.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
    private final ArrayList monomials;

    Polynomial() {
        monomials = new ArrayList<Monomial>();
    }

    Polynomial(final Monomial[] monomials) {
        this.monomials = new ArrayList<Monomial>(Arrays.asList(monomials));
    }
}

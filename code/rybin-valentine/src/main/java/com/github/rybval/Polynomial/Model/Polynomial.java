package com.github.rybval.Polynomial.Model;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Polynomial {
    private final Map<Integer, Monomial> monomials;

    Polynomial() {
        monomials = new HashMap<Integer, Monomial>();
    }

    Polynomial(final List<Monomial> monomialsList) {
        monomials = new HashMap<Integer, Monomial>();
        for (Monomial monomialIn : monomialsList) {
            int power = monomialIn.getPower();
            Monomial monomialToPut;
            if (monomials.containsKey(power)) {
                monomialToPut = monomials.get(power).add(monomialIn);
            } else {
                monomialToPut = monomialIn;
            }
            monomials.put(power, monomialToPut);
        }
    }

    Polynomial(final Monomial[] monomials) {
        this(Arrays.asList(monomials));
    }

    public static Polynomial fromString(final String string) {
        String[] monomialStrings = string.replaceAll(" *- *", " -")
                                         .replaceAll(" *\\+ *", " +")
                                         .split(" +");
        List<Monomial> monomials = new ArrayList<Monomial>();
        for (String monomialString : monomialStrings) {
            monomials.add(Monomial.fromString(monomialString));
        }
        return new Polynomial(monomials);
    }
}

package com.github.rybval.Polynomial.Model;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;

public class Polynomial {
    private final Map<Integer, Monomial> monomials;

    Polynomial() {
        monomials = new TreeMap<Integer, Monomial>();
    }

    Polynomial(final List<Monomial> monomialsList) {
        monomials = new TreeMap<Integer, Monomial>();
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
            if (!monomialString.isEmpty()) {
                monomials.add(Monomial.fromString(monomialString));
            }
        }
        return new Polynomial(monomials);
    }

    @Override
    public String toString() {
        String string = "";
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            String monomialStr = entry.getValue().toString();
            if (monomialStr.startsWith("-")) {
                string += " - " + monomialStr.substring("-".length());
            } else {
                string += " + " + monomialStr;
            }
        }
        if (string.startsWith(" + ")) {
            string = string.substring(" + ".length());
        } else {
            string = "-" + string.substring(" - ".length());
        }
        return string;
    }
}

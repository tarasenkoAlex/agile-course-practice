package com.github.rybval.Polynomial.Model;

import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class Polynomial {
    private final Map<Integer, Monomial> monomials;

    Polynomial() {
        monomials = new TreeMap<Integer, Monomial>();
    }

    Polynomial(final Collection<Monomial> monomialsCollection) {
        monomials = new TreeMap<Integer, Monomial>();
        for (Monomial monomialIn : monomialsCollection) {
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
        Collection<Monomial> monomials = new ArrayList<Monomial>();
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

    @Override
    public int hashCode() {
        return monomials.hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof Polynomial) {
            Polynomial polynomial = (Polynomial) object;
            return this.monomials.equals(polynomial.monomials);
        } else {
            return false;
        }
    }

    private Collection<Monomial> opWithPolynomial(
                       final Polynomial another,
                       final BiConsumer<ArrayList<Monomial>, Monomial> work) {
        ArrayList<Monomial> newMonomials = new ArrayList<Monomial>();
        newMonomials.addAll(this.monomials.values());
        for (Monomial monomial : another.monomials.values()) {
            work.accept(newMonomials, monomial);
        }
        return newMonomials;
    }

    public Polynomial add(final Polynomial another) {
        return new Polynomial(opWithPolynomial(another,
                   (ArrayList<Monomial> summMonomials, Monomial addMonomial) -> {
                                               summMonomials.add(addMonomial);
                                            }));
    }

    public Polynomial subtract(final Polynomial subtrahend) {
        return new Polynomial(opWithPolynomial(subtrahend,
                    (ArrayList<Monomial> diffMonomials, Monomial subtMonomial) -> {
                                        diffMonomials.add(subtMonomial.negate());
                                    }));
    }

    public Polynomial multiply(final Polynomial multiplier) {
        return new Polynomial(opWithPolynomial(multiplier,
            (ArrayList<Monomial> multMonomials, Monomial multMonomial) -> {
                for (int i = 0; i < multMonomials.size(); i++) {
                    multMonomials.set(i, multMonomials.get(i).multiply(multMonomial));
                }
            }));
    }
}

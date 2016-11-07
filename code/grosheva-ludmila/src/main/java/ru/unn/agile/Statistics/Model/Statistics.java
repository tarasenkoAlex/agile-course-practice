package ru.unn.agile.Statistics.Model;

import java.security.InvalidParameterException;
import static java.lang.Math.abs;
import static java.lang.Math.pow;


public class Statistics {
   private final double []x;
   private final double []p;
   public static final double DELTA = 0.00001;

    Statistics(final double []x, final double []p) {

        double sum = 0;
        if (x.length != p.length) {
            throw new InvalidParameterException("Possibilities and Values must have same sizes");
        }
        for (int i = 0; i < x.length; ++i) {
            sum += p[i];
            if (p[i] < 0 || p[i] > 1) {
                throw new InvalidParameterException("Possibility must be from 0 to 1");
            }
        }
        if (abs(sum - 1) > DELTA) {
            throw new InvalidParameterException("PossibilitySum must equals to 1");
        }
        this.x = x;
        this.p = p;

    }

   double computeInitialMoment(final int k) {
       double initialMoment = 0;
       for (int i = 0; i < x.length; i++) {
           initialMoment += pow(x[i], k) * p[i];
       }
       return initialMoment;
   }


  double  computeExpectedValue() {
      return computeInitialMoment(1);
    }

   double computeDispersion() {
        return computeCentralMoment(2);
    }

    double computeCentralMoment(final int k) {
        double centralMoment = 0;
        for (int i = 0; i < x.length; i++) {
            centralMoment += pow(x[i] - computeExpectedValue(), k) * p[i];
        }
        return centralMoment;
    }

    private static double sqr(final double x) {
        return x * x;
    }

    public double computeAbsoluteInitialMoment(final int k) {
        double absoluteInitialMoment = 0;
        for (int i = 0; i < x.length; i++) {
            absoluteInitialMoment += pow(abs(x[i]), k) * p[i];
        }
        return absoluteInitialMoment;
    }


    public double computeAbsoluteCentralMoment(final int k) {
        double absoluteCentralMoment = 0;
        for (int i = 0; i < x.length; i++) {
            absoluteCentralMoment += pow(abs(x[i] - computeExpectedValue()), k) * p[i];
        }
        return absoluteCentralMoment;
    }
double factorial(final double x, final int k) {
    double res = 1;
    for (int i = 0; i < k; ++i) {
        res *= x - i;
    }
    return res;
}

    public double computeFactorialMoment(final int k) {
        double factorialMoment = 0;
        for (int i = 0; i < x.length; i++) {
            factorialMoment += factorial(x[i], k) * p[i];
        }
        return factorialMoment;
    }
}


package ru.unn.agile.NewtonRoots.Model;

import static java.lang.Double.NaN;

interface FunctionInterface {
    double compute(double x);
}

class NewtonMethod {
    public enum StoppingCriterion { FunctionModule, DifferenceBetweenApproximates }
    public enum ResultStatus { RootSuccessfullyFound, NoRootInInterval,
        NonmonotonicFunctionOnInterval, InitialPointOutsideInterval, IncorrectIntervalBoundaries }
    private StoppingCriterion stoppingCriterion;
    private ResultStatus resultStatus;
    private double accuracyEps;
    private double derivativeStep;
    public static final double DEFAULT_EPS = 1e-10;
    public static final double DEFAULT_DERIVATIVE_STEP = 1e-10;
    public static final double MONOTONIC_CHECK_STEP = 1e-5;
    private final StoppingCriterion defaultStoppingCriterion = StoppingCriterion.FunctionModule;

    private final StoppingCriterionInterface stoppingCriterionAsFunctionModule =
            (func, x, xPrev, eps) -> Math.abs(func.compute(x)) < eps;
    private final StoppingCriterionInterface stoppingCriterionAsDiffBetweenApprox =
            (func, x, xPrev, eps) -> Math.abs(x - xPrev) < eps;
    private StoppingCriterionInterface currentStoppingCriterionFunction;

    private interface StoppingCriterionInterface {
        boolean check(FunctionInterface func, double x, double xPrev, double accuracy);
    }

    NewtonMethod(final double accuracy, final double derivativeComputeStep) {
        accuracyEps = accuracy;
        derivativeStep = derivativeComputeStep;
        currentStoppingCriterionFunction = stoppingCriterionAsFunctionModule;
    }

    private boolean isMonotonicFunctionOnInterval(final FunctionInterface func,
                                                  final double intervalStart,
                                                  final double intervalEnd) {
        double x = intervalStart;
        double xStep = MONOTONIC_CHECK_STEP;
        double val = func.compute(x + xStep), dif;
        double oldVal = func.compute(x);
        double oldDif = val - oldVal;
        x += xStep;

        while (x <= intervalEnd) {
            x += xStep;
            val = func.compute(x);
            dif = val - oldVal;
            if (Math.signum(dif) != Math.signum(oldDif)) {
                return false;
            }
            oldVal = val;
            oldDif = dif;
        }
        return true;
    }

    private boolean isMonotonicFunctionHasRoot(final FunctionInterface func,
                                               final double intervalStart,
                                               final double intervalEnd) {
        return func.compute(intervalStart) * func.compute(intervalEnd) <= 0;
    }

    double findRoot(final FunctionInterface func, final double initialPoint,
                    final double intervalStart, final double intervalEnd) {
        double x0 = initialPoint;
        double x = x0;
        double xPrev = x0;
        double h = derivativeStep;

        if (intervalEnd <= intervalStart) {
            resultStatus = ResultStatus.IncorrectIntervalBoundaries;
            return NaN;
        }

        if (initialPoint < intervalStart || initialPoint > intervalEnd) {
            resultStatus = ResultStatus.InitialPointOutsideInterval;
            return NaN;
        }

        boolean isMonotonic = isMonotonicFunctionOnInterval(func, intervalStart, intervalEnd);
        if (!isMonotonic) {
            resultStatus = ResultStatus.NonmonotonicFunctionOnInterval;
            return NaN;
        }

        boolean isHasRoot = isMonotonicFunctionHasRoot(func, intervalStart, intervalEnd);
        if (!isHasRoot) {
            resultStatus = ResultStatus.NoRootInInterval;
            return NaN;
        }

        do {
            x = x - func.compute(x) / (func.compute(x + h) - func.compute(x)) * h;
            while (x < intervalStart || x > intervalEnd) {
                x = (x + xPrev) / 2;
            }
            xPrev = x;
        } while (!currentStoppingCriterionFunction.check(func, x, xPrev, accuracyEps));
        resultStatus = ResultStatus.RootSuccessfullyFound;
        return x;
    }

    public void setStoppingCriterion(final StoppingCriterion newStoppingCriterion) {
        stoppingCriterion = newStoppingCriterion;
        switch (stoppingCriterion) {
            case FunctionModule:
                currentStoppingCriterionFunction = stoppingCriterionAsFunctionModule;
                break;
            case DifferenceBetweenApproximates:
                currentStoppingCriterionFunction = stoppingCriterionAsDiffBetweenApprox;
                break;
            default:
                break;
        }
    }

    public StoppingCriterion getStoppingCriterion() {
        return stoppingCriterion;
    }

    public void setAccuracyEps(final double accuracy) throws Exception {
        if (accuracy > 0) {
            accuracyEps = accuracy;
        } else {
            throw new Exception("Accuracy is incorrect");
        }
    }

    public double getAccuracyEps() {
        return accuracyEps;
    }

    public void setDerivativeStep(final double derivativeComputeStep) throws Exception {
        if (derivativeComputeStep > 0) {
            derivativeStep = derivativeComputeStep;
        } else {
            throw new Exception("Derivative step is incorrect");
        }
    }

    public double getDerivativeStep() {
        return derivativeStep;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

}

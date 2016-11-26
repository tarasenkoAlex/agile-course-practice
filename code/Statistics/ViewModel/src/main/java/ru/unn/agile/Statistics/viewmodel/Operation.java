package ru.unn.agile.Statistics.viewmodel;

import ru.unn.agile.Statistics.model.Statistics;


public enum Operation {
    EV("Expected value", new ComputeExpectedValue()),
    VAR("Variance", new ComputeVariance()),
    IM("Initial moment", new ComputeInitialMoment());

    private final String name;
    private final Object computeObject;

    Operation(final String name, final Object computeObject) {
        this.computeObject = computeObject;
        this.name = name;
    }

    Computable toComputable() {
        return (Computable) computeObject;
    }
    ComputableWithMomentOrder toComputableWithOrder() {
        return (ComputableWithMomentOrder) computeObject;
    }
    boolean is(final Class cls) {
        return cls.isInstance(computeObject);
    }

    @Override
    public String toString() {
        return name;
    }
}


interface Computable {
    double compute(Statistics statistics);
}

interface ComputableWithMomentOrder {
    double compute(Statistics statistics, int order);
}


class ComputeExpectedValue implements Computable {
    @Override
    public double compute(final Statistics statistics) {
        return statistics.computeExpectedValue();
    }
}

class ComputeVariance implements Computable {
    @Override
    public double compute(final Statistics statistics) {
        return statistics.computeVariance();
    }
}

class ComputeInitialMoment implements ComputableWithMomentOrder {
    @Override
    public double compute(final Statistics statistics, final int order) {
        return statistics.computeInitialMoment(order);
    }
}

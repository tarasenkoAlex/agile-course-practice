package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeQuadraticEquationSolverLogger implements IQuadraticEquationSolverLogger {
    private final ArrayList<String> fakeLog = new ArrayList<>();

    @Override
    public void makeLog(final String s) {
        fakeLog.add(s);
    }

    public List<String> getLog() {
        return fakeLog;
    }
}

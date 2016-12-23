package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import java.util.List;

public interface IQuadraticEquationSolverLogger {
    void makeLog(String s);
    List<String> getLog();
}

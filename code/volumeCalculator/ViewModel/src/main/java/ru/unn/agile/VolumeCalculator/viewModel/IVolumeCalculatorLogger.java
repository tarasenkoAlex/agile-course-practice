package ru.unn.agile.VolumeCalculator.viewModel;

import java.util.List;

/**
 * Created by Elena on 12/18/2016.
 */
public interface IVolumeCalculatorLogger {
    void addLog(String s);
    List<String> getLog();
}

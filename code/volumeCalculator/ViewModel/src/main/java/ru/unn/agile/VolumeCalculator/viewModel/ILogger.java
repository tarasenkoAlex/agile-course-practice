package ru.unn.agile.VolumeCalculator.viewModel;

import java.util.List;

/**
 * Created by Elena on 12/18/2016.
 */
public interface ILogger {
    void log(String s);

    List<String> getLog();
}

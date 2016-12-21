package ru.unn.agile.VolumeCalculator.viewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 12/18/2016.
 */
public class FakeVolumeCalculatorLogger implements IVolumeCalculatorLogger {
    private final ArrayList<String> log = new ArrayList<>();
    @Override
    public void addLog(final String s) {
        log.add(s);
    }
    @Override
    public List<String> getLog() {
        return log;
    }
}

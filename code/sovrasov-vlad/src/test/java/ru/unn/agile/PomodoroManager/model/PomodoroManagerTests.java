package ru.unn.agile.PomodoroManager.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PomodoroManagerTests {
    private final double delta = 0.001;

    @Test
    public void defaultState()  {
        PomodoroManager manager = new PomodoroManager();
        assertEquals(manager.getState(), PomodoroState.Off);
    }

    @Test
    public void resetState()  {
        PomodoroManager manager = new PomodoroManager();
        manager.resetState();
        assertEquals(manager.getState(), PomodoroState.Off);
    }
}

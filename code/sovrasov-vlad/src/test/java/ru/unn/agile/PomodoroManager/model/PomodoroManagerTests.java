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
        assertEquals(manager.getCheckmarksCounter(), 0);
    }

    @Test
    public void startCycle()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        assertEquals(manager.getState(), PomodoroState.Pomodoro);
        assertEquals(manager.getCheckmarksCounter(), 0);
    }

    @Test
    public void switchToShortBreak()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        manager.nextState();
        assertEquals(manager.getState(), PomodoroState.ShortBreak);
        assertEquals(manager.getCheckmarksCounter(), 1);
    }

    @Test
    public void switchToNextPomodoro()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        manager.nextState();
        manager.nextState();
        assertEquals(manager.getState(), PomodoroState.Pomodoro);
        assertEquals(manager.getCheckmarksCounter(), 1);
    }

    @Test
    public void switchToLongBreak()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        manager.nextState();

        manager.nextState();
        manager.nextState();

        manager.nextState();
        manager.nextState();

        manager.nextState();
        manager.nextState();
        assertEquals(manager.getState(), PomodoroState.LongBreak);
        assertEquals(manager.getCheckmarksCounter(), 4);
    }
}

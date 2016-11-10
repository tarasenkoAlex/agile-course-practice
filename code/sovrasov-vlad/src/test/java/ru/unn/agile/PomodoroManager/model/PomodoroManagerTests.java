package ru.unn.agile.PomodoroManager.model;

import org.junit.Test;

import javax.management.RuntimeErrorException;

import static org.junit.Assert.*;

public class PomodoroManagerTests {
    private final double delta = 0.001;

    private void skipState(final PomodoroManager manager, final int times)  {
        for (int i = 0; i < times; i++)  {
            manager.nextState();
        }
    }

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

    @Test(expected = RuntimeErrorException.class)
    public void doubleStartCycle()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        manager.startCycle();
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
        skipState(manager, 2);
        assertEquals(manager.getState(), PomodoroState.Pomodoro);
        assertEquals(manager.getCheckmarksCounter(), 1);
    }

    @Test
    public void switchToLongBreak()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        skipState(manager, 7);
        assertEquals(manager.getState(), PomodoroState.LongBreak);
        assertEquals(manager.getCheckmarksCounter(), 4);
    }

    @Test(expected = RuntimeErrorException.class)
    public void throwThen()  {
        PomodoroManager manager = new PomodoroManager();
        manager.startCycle();
        skipState(manager, 9);
        assertEquals(manager.getState(), PomodoroState.Off);
        assertEquals(manager.getCheckmarksCounter(), 0);
    }
}

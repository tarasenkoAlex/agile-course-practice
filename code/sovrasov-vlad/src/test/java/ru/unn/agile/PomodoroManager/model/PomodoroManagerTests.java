package ru.unn.agile.PomodoroManager.model;

import org.junit.Test;
import javax.management.RuntimeErrorException;

import static org.junit.Assert.*;

public class PomodoroManagerTests {
    private PomodoroManager manager = new PomodoroManager();

    private void skipState(final int times)  {
        for (int i = 0; i < times; i++)  {
            manager.nextState();
        }
    }

    @Test
    public void defaultState()  {
        assertEquals(manager.getState(), PomodoroState.Off);
    }

    @Test
    public void resetState()  {
        manager.resetState();
        assertEquals(manager.getState(), PomodoroState.Off);
        assertEquals(manager.getCheckmarksCounter(), 0);
    }

    @Test
    public void startCycle()  {
        manager.startCycle();
        assertEquals(manager.getState(), PomodoroState.Pomodoro);
        assertEquals(manager.getCheckmarksCounter(), 0);
    }

    @Test(expected = RuntimeErrorException.class)
    public void throwWhenDoubleStartCycle()  {
        manager.startCycle();
        manager.startCycle();
    }

    @Test
    public void switchToShortBreak()  {
        manager.startCycle();
        manager.nextState();
        assertEquals(manager.getState(), PomodoroState.ShortBreak);
        assertEquals(manager.getCheckmarksCounter(), 1);
    }

    @Test
    public void switchToNextPomodoro()  {
        manager.startCycle();
        skipState(2);
        assertEquals(manager.getState(), PomodoroState.Pomodoro);
        assertEquals(manager.getCheckmarksCounter(), 1);
    }

    @Test
    public void switchToLongBreak()  {
        manager.startCycle();
        skipState(7);
        assertEquals(manager.getState(), PomodoroState.LongBreak);
        assertEquals(manager.getCheckmarksCounter(), 4);
    }

    @Test
    public void switchToOffAfterCycle()  {
        manager.startCycle();
        skipState(8);
        assertEquals(manager.getState(), PomodoroState.Off);
        assertEquals(manager.getCheckmarksCounter(), 0);
    }

    @Test(expected = RuntimeErrorException.class)
    public void throwThenStateOutOfRange()  {
        manager.startCycle();
        skipState(9);
    }

    @Test
    public void cyclesCounterIncreasedAfterCycle() {
        manager.startCycle();
        skipState(8);
        assertEquals(manager.getFinishedCyclesCounter(), 1);
    }
}

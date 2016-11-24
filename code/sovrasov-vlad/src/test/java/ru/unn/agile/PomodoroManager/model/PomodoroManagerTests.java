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
        assertEquals(PomodoroState.Off, manager.getState());
    }

    @Test
    public void resetState()  {
        manager.resetState();
        assertEquals(PomodoroState.Off, manager.getState());
        assertEquals(0, manager.getCheckmarksCounter());
    }

    @Test
    public void startCycle()  {
        manager.startCycle();
        assertEquals(PomodoroState.Pomodoro, manager.getState());
        assertEquals(0, manager.getCheckmarksCounter());
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
        assertEquals(PomodoroState.ShortBreak, manager.getState());
        assertEquals(1, manager.getCheckmarksCounter());
    }

    @Test
    public void switchToNextPomodoro()  {
        manager.startCycle();
        skipState(2);
        assertEquals(PomodoroState.Pomodoro, manager.getState());
        assertEquals(1, manager.getCheckmarksCounter());
    }

    @Test
    public void switchToLongBreak()  {
        manager.startCycle();
        skipState(7);
        assertEquals(PomodoroState.LongBreak, manager.getState());
        assertEquals(4, manager.getCheckmarksCounter());
    }

    @Test
    public void switchToOffAfterCycle()  {
        manager.startCycle();
        skipState(8);
        assertEquals(PomodoroState.Off, manager.getState());
        assertEquals(0, manager.getCheckmarksCounter());
    }

    @Test(expected = RuntimeErrorException.class)
    public void throwWhenStateOutOfRange()  {
        manager.startCycle();
        skipState(9);
    }

    @Test
    public void cyclesCounterIncreasedAfterCycle() {
        manager.startCycle();
        skipState(8);
        assertEquals(1, manager.getFinishedCyclesCounter());
    }
}

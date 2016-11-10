package ru.unn.agile.PomodoroManager.model;

public class PomodoroManager {
    private PomodoroState state;
    private int checkmarksCounter;

    public PomodoroManager() {
        state = PomodoroState.Off;
        checkmarksCounter = 0;
    }

    public PomodoroState getState()  {
        return state;
    }

    public void resetState()  {
        state = PomodoroState.Off;
        checkmarksCounter = 0;
    }

    public void startCycle()  {
        state = PomodoroState.Pomodoro;
        checkmarksCounter = 1;
    }

    public void nextState()  {
        state = PomodoroState.ShortBreak;
    }

    public int getCheckmarksCounter()  {
        return checkmarksCounter;
    }
}

package ru.unn.agile.PomodoroManager.model;

public class PomodoroManager {
    private PomodoroState state;

    public PomodoroManager() {
        state = PomodoroState.Off;
    }

    public PomodoroState getState()  {
        return state;
    }

    public void resetState()  {
        state = PomodoroState.Off;
    }
}

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
        checkmarksCounter = 0;
    }

    public void nextState()  {
        final int pomodorosPerCycle = 4;
        switch (state)  {
            case Pomodoro:
                if (checkmarksCounter < pomodorosPerCycle - 1)  {
                    state = PomodoroState.ShortBreak;
                }  else if (checkmarksCounter == pomodorosPerCycle - 1)  {
                    state = PomodoroState.LongBreak;
                }
                checkmarksCounter++;
                break;
            case LongBreak:
                break;
            case ShortBreak:
                state = PomodoroState.Pomodoro;
                break;
            default:
                break;
        }
    }

    public int getCheckmarksCounter()  {
        return checkmarksCounter;
    }
}

package ru.unn.agile.PomodoroManager.model;

import javax.management.RuntimeErrorException;

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
        if (state != PomodoroState.Off) {
            throw new RuntimeErrorException(new Error("Bad state"));
        }
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
                state = PomodoroState.Off;
                checkmarksCounter = 0;
                break;
            case ShortBreak:
                state = PomodoroState.Pomodoro;
                break;
            default:
                throw new RuntimeErrorException(new Error("Bad state"));
        }
    }

    public int getCheckmarksCounter()  {
        return checkmarksCounter;
    }
}

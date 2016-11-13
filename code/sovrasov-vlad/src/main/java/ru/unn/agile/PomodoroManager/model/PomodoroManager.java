package ru.unn.agile.PomodoroManager.model;

import javax.management.RuntimeErrorException;

public class PomodoroManager {
    private PomodoroState state;
    private int checkmarksCounter;
    private int finishedCyclesCounter;
    private static final int POMODOROS_PER_CYCLE = 4;

    public PomodoroManager() {
        state = PomodoroState.Off;
        checkmarksCounter = 0;
        finishedCyclesCounter = 0;
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
            throw new RuntimeErrorException(new Error("Cycle already started"));
        }
        state = PomodoroState.Pomodoro;
        checkmarksCounter = 0;
    }

    public void nextState()  {
        switch (state)  {
            case Pomodoro:
                checkmarksCounter++;
                if (checkmarksCounter < POMODOROS_PER_CYCLE)  {
                    state = PomodoroState.ShortBreak;
                }  else if (checkmarksCounter == POMODOROS_PER_CYCLE)  {
                    state = PomodoroState.LongBreak;
                }
                break;
            case LongBreak:
                resetState();
                finishedCyclesCounter++;
                break;
            case ShortBreak:
                state = PomodoroState.Pomodoro;
                break;
            default:
                throw new RuntimeErrorException(new Error(""));
        }
    }

    public int getCheckmarksCounter()  {
        return checkmarksCounter;
    }

    public int getFinishedCyclesCounter()  {
        return finishedCyclesCounter;
    }
}

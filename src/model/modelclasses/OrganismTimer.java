package model.modelclasses;

import java.util.Timer;
import java.util.TimerTask;

public class OrganismTimer {

    //express in milliseconds => 1000 milliseconds = 1 sec
    private static final int[] FIXED_DELAY = {125, 250, 500, 1000, 2000, 3000, 4000, 5000};

    private static Timer timer;
    private static TimerTask timerTask;
    private static int DEFAULT_DELAY_INDEX_ARRAY = 3;
    private static int defaultDelay = FIXED_DELAY[DEFAULT_DELAY_INDEX_ARRAY];
    private static boolean isTimerPause;

    private static boolean isTimerCreated = false;

    public OrganismTimer() {

    }

    public static void createTimer() {
        timer = new Timer();
        isTimerPause = false;
        isTimerCreated = true;
    }

    public static void startTimer() {
        if (isTimerCreated) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(!isTimerPause) {
                        OrganismManager.printOrganism();
                        OrganismManager.generateNextGeneration();
                    }
                }
            };
            timer.schedule(timerTask, 0, defaultDelay);
        }
    }

    public static void setDelayUp() {
        if (isTimerCreated) {
            timerTask.cancel();

            if (DEFAULT_DELAY_INDEX_ARRAY < (FIXED_DELAY.length-1) ) {
                DEFAULT_DELAY_INDEX_ARRAY++;
                defaultDelay = FIXED_DELAY[DEFAULT_DELAY_INDEX_ARRAY];
            }
            startTimer();
        }
    }

    public static void setDelayDown() {
        if (isTimerCreated) {
            timerTask.cancel();

            if (DEFAULT_DELAY_INDEX_ARRAY > 0 ) {
                DEFAULT_DELAY_INDEX_ARRAY--;
                defaultDelay = FIXED_DELAY[DEFAULT_DELAY_INDEX_ARRAY];
            }
            startTimer();
        }
    }

    public static void pauseTimer() {
        if (isTimerCreated) {
            isTimerPause = true;
            timerTask.cancel();
        }
    }

    public static void unpauseTimer() {
        if (isTimerCreated) {
            isTimerPause = false;
            startTimer();
        }
    }

}

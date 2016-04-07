package de.briemla.clockradio;

import java.util.Timer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AlarmTimer implements ChangeListener<Object> {

    private Timer startTimer;
    private final Alarm alarm;
    private Timer stopTimer;

    public AlarmTimer(Alarm alarm) {
        super();
        this.alarm = alarm;
    }

    @Override
    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
        stopTimer();
        startTimer();
    }

    void stopTimer() {
        if (startTimer != null) {
            startTimer.cancel();
            startTimer = null;
        }
        if (stopTimer != null) {
            stopTimer.cancel();
            stopTimer = null;
        }
    }

    void startTimer() {
        startTimer = new Timer("AlarmTimer", true);
        // TODO: Maybe use Timer#schedule to run the timer on specified
        // dates:
        // Timer timer new Timer();
        // Thread myThread= // Your thread
        // Calendar date = Calendar.getInstance();
        // date.set(
        // Calendar.DAY_OF_WEEK,
        // Calendar.SUNDAY
        // );
        // date.set(Calendar.HOUR, 0);
        // date.set(Calendar.MINUTE, 0);
        // date.set(Calendar.SECOND, 0);
        // date.set(Calendar.MILLISECOND, 0);
        // // Schedule to run every Sunday in midnight
        // timer.schedule(
        // new SampleTask (myThread),
        // date.getTime(),
        // 1000 * 60 * 60 * 24 * 7
        // );
        startTimer.scheduleAtFixedRate(new AlarmTask(alarm), alarm.alarmDate(), Long.MAX_VALUE);
        stopTimer = new Timer("AlarmStopTimer", true);
        stopTimer.scheduleAtFixedRate(new AlarmStopTask(alarm), alarm.alarmStopDate(), Long.MAX_VALUE);
    }
}
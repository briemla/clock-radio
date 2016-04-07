package de.briemla.clockradio;

import java.util.TimerTask;

public class AlarmTask extends TimerTask {

    private final Alarm alarm;

    public AlarmTask(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void run() {
        alarm.play();
    }
}
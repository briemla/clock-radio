package de.briemla.clockradio;

import java.util.TimerTask;

public class AlarmStopTask extends TimerTask {

    private final Alarm alarm;

    public AlarmStopTask(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void run() {
        alarm.stop();
    }
}
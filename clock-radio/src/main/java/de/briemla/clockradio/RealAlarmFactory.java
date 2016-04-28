package de.briemla.clockradio;

import java.io.File;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;

// TODO find a better name
public class RealAlarmFactory implements AlarmFactory {

    private static final int wakeUpField = 0;
    private static final int mediaField = 1;
    private static final int activeDaysField = 2;
    private static final int activatedField = 3;
    private static final String separator = ";";
    private final TimeProvider timeProvider;
    private final PlayerFactory playerFactory;
    private SaveTrigger saveTrigger;

    public RealAlarmFactory(PlayerFactory playerFactory, TimeProvider timeProvider) {
        super();
        this.playerFactory = playerFactory;
        this.timeProvider = timeProvider;
        saveTrigger = () -> {};
    }

    public void initialize(SaveTrigger saveTrigger) {
        this.saveTrigger = saveTrigger;
    }

    @Override
    public Alarm create() {
        return new Alarm(playerFactory, timeProvider, saveTrigger);
    }

    @Override
    public Alarm fromStorage(String storedAlarm) {
        Alarm alarm = new Alarm(playerFactory, timeProvider, saveTrigger);
        String[] fromStoredAlarm = storedAlarm.split(separator);
        alarm.wakeUpTimeProperty().set(toWakeUpTime(fromStoredAlarm));
        alarm.mediaProperty().set(toMedia(fromStoredAlarm));
        alarm.activeDaysProperty().set(toActiveDays(fromStoredAlarm));
        alarm.activatedProperty().setValue(isActivated(fromStoredAlarm));
        return alarm;
    }

    private Boolean isActivated(String[] fromStoredAlarm) {
        return Boolean.valueOf(fromStoredAlarm[activatedField]);
    }

    private ActiveDays toActiveDays(String[] fromStoredAlarm) {
        String activeDays = fromStoredAlarm[activeDaysField];
        return ActiveDays.from(activeDays);
    }

    private Media toMedia(String[] values) {
        String mediaValue = values[mediaField];
        return new LocalFolder(new File(mediaValue).toPath());
    }

    private WakeUpTime toWakeUpTime(String[] values) {
        return WakeUpTime.from(values[wakeUpField]);
    }
}

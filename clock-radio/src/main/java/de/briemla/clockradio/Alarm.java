package de.briemla.clockradio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import de.briemla.clockradio.controls.LocalFolder;

public class Alarm {

    private final SimpleObjectProperty<Duration> durationProperty;
    private final SimpleBooleanProperty alarmStartedProperty;
    private final SimpleBooleanProperty alarmAlreadyStartedProperty;
    private final SimpleObjectProperty<ActiveDays> activeDaysProperty;
    private final SimpleObjectProperty<Media> mediaProperty;
    private final SimpleObjectProperty<WakeUpTime> wakeUpTimeProperty;
    private final SimpleBooleanProperty activated = new SimpleBooleanProperty(true);
    private final PlayerFactory playerFactory;

    public Alarm(SimpleBooleanProperty alarmAlreadyStartedProperty, PlayerFactory player) {
        this.alarmAlreadyStartedProperty = alarmAlreadyStartedProperty;
        playerFactory = player;
        durationProperty = new SimpleObjectProperty<>(Duration.ofHours(1));
        alarmStartedProperty = new SimpleBooleanProperty();
        activeDaysProperty = new SimpleObjectProperty<>(new ActiveDays());
        mediaProperty = new SimpleObjectProperty<>(new LocalFolder());
        wakeUpTimeProperty = new SimpleObjectProperty<>(initialWakeUpTime());
        AlarmTimer alarmTimer = new AlarmTimer(this);
        wakeUpTimeProperty.addListener(alarmTimer);
        activeDaysProperty.addListener(alarmTimer);
        alarmTimer.startTimer();
        activated.addListener((change, oldValue, newValue) -> {
            if (newValue) {
                alarmTimer.startTimer();
                return;
            }
            alarmTimer.stopTimer();
        });
    }

    private static WakeUpTime initialWakeUpTime() {
        LocalTime now = LocalTime.now()
                                 .plusMinutes(1);
        return new WakeUpTime(now.getHour(), now.getMinute());
    }

    // TODO restart timer
    public void stop() {
        // if (alarmStartedProperty.get()) {
        // mediaProperty().get()
        // .stop(playerFactory);
        // alarmStartedProperty.set(false);
        // }
    }

    public Duration getDuration() {
        return durationProperty.get();
    }

    public ObjectProperty<ActiveDays> activeDaysProperty() {
        return activeDaysProperty;
    }

    public ObjectProperty<Media> mediaProperty() {
        return mediaProperty;
    }

    public ObjectProperty<WakeUpTime> wakeUpTimeProperty() {
        return wakeUpTimeProperty;
    }

    public boolean play() {
        // if (alarmAlreadyStartedProperty.get()) {
        // return false;
        // }
        // alarmStartedProperty.set(true);
        // try {
        // mediaProperty.get()
        // .play(playerFactory);
        // return true;
        // } catch (Exception exception) {
        // exception.printStackTrace();
        // alarmStartedProperty.set(false);
        // }
        // return false;
        PlayerWorker player = playerFactory.create(mediaProperty.get());
        player.start();
        return true;
    }

    public ReadOnlyBooleanProperty alarmStartedProperty() {
        return alarmStartedProperty;
    }

    Date alarmDate() {
        return convertToDate(alarmLocalDate());
    }

    private static Date convertToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault())
                             .toInstant());
    }

    private LocalDateTime alarmLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextTime = wakeUpTimeProperty.get()
                                                   .nextAlarm(now);
        return activeDaysProperty.get()
                                 .nextAlarm(nextTime);
    }

    Date alarmStopDate() {
        return convertToDate(alarmLocalDate().plus(getDuration()));
    }

    public Property<Boolean> activatedProperty() {
        return activated;
    }

    public void kill() {
        stop();
        activated.set(false);
    }

}

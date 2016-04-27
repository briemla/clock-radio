package de.briemla.clockradio;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class Alarm {

    private static final String separator = ";";
    private final SimpleObjectProperty<Duration> durationProperty;
    private final SimpleBooleanProperty alarmStartedProperty;
    private final SimpleObjectProperty<ActiveDays> activeDaysProperty;
    private final SimpleObjectProperty<Media> mediaProperty;
    private final SimpleObjectProperty<WakeUpTime> wakeUpTimeProperty;
    private final SimpleBooleanProperty activated = new SimpleBooleanProperty(true);
    private final PlayerFactory playerFactory;
    private Optional<PlayerWorker> player;

    private final TimeProvider timeProvider;

    public Alarm(PlayerFactory playerFactory, TimeProvider timeProvider, SaveTrigger storage) {
        this.timeProvider = timeProvider;
        player = Optional.empty();
        this.playerFactory = playerFactory;
        durationProperty = new SimpleObjectProperty<>(Duration.ofHours(1));
        alarmStartedProperty = new SimpleBooleanProperty();
        activeDaysProperty = new SimpleObjectProperty<>(new ActiveDays());
        mediaProperty = new SimpleObjectProperty<>(new LocalFolder());
        wakeUpTimeProperty = new SimpleObjectProperty<>(initialWakeUpTime(timeProvider));
        saveOnChangeTo(storage);
        // AlarmTimer alarmTimer = new AlarmTimer(this);
        // wakeUpTimeProperty.addListener(alarmTimer);
        // activeDaysProperty.addListener(alarmTimer);
        // alarmTimer.startTimer();
        // activated.addListener((change, oldValue, newValue) -> {
        // if (newValue) {
        // alarmTimer.startTimer();
        // return;
        // }
        // alarmTimer.stopTimer();
        // });
    }

    private void saveOnChangeTo(SaveTrigger storage) {
        wakeUpTimeProperty.addListener((change, oldValue, newValue) -> storage.save());
        mediaProperty.addListener((change, oldValue, newValue) -> storage.save());
        activeDaysProperty.addListener((change, oldValue, newValue) -> storage.save());
        activated.addListener((change, oldValue, newValue) -> storage.save());
    }

    private static WakeUpTime initialWakeUpTime(TimeProvider timeProvider) {
        LocalTime now = timeProvider.nextMinute();
        return new WakeUpTime(now.getHour(), now.getMinute());
    }

    public void stop() {
        player.ifPresent(PlayerWorker::stop);
        player = Optional.empty();
    }

    private Duration getDuration() {
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

    public boolean play(LocalDateTime atTime) {
        if (withinOneMinute(atTime)) {
            return false;
        }
        PlayerWorker player = playerFactory.create(mediaProperty.get());
        this.player = Optional.of(player);
        player.start();
        return true;
    }

    private boolean withinOneMinute(LocalDateTime atTime) {
        LocalDateTime alarmDate = alarmLocalDate().withSecond(0).withNano(0);
        LocalDateTime roundedStart = atTime.withSecond(0).withNano(0);
        return !alarmDate.isEqual(roundedStart);
    }

    public ReadOnlyBooleanProperty alarmStartedProperty() {
        return alarmStartedProperty;
    }

    Date alarmDate() {
        return convertToDate(alarmLocalDate());
    }

    private static Date convertToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDateTime alarmLocalDate() {
        LocalDateTime now = today();
        LocalDateTime nextTime = wakeUpTimeProperty.get().nextAlarm(now);
        return activeDaysProperty.get().nextAlarm(nextTime);
    }

    private LocalDateTime today() {
        return timeProvider.today();
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

    public void storeTo(PrintStream output) {
        String wakeUpTime = wakeUpTimeProperty.get().toString();
        String media = mediaProperty.get().toString();
        String activeDays = activeDaysProperty.get().serialize();
        output.println(wakeUpTime + separator + media + separator + activeDays);
    }

}

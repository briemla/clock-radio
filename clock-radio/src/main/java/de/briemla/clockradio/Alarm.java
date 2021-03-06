package de.briemla.clockradio;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class Alarm {

    private static final String newLine = System.lineSeparator();
    private static final String separator = ";";
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
        activeDaysProperty = new SimpleObjectProperty<>(new ActiveDays());
        mediaProperty = new SimpleObjectProperty<>(new LocalFolder());
        wakeUpTimeProperty = new SimpleObjectProperty<>(initialWakeUpTime(timeProvider));
        saveOnChangeTo(storage);
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

    private LocalDateTime alarmLocalDate() {
        LocalDateTime now = today();
        LocalDateTime nextTime = wakeUpTimeProperty.get().nextAlarm(now);
        return activeDaysProperty.get().nextAlarm(nextTime);
    }

    private LocalDateTime today() {
        return timeProvider.today();
    }

    public Property<Boolean> activatedProperty() {
        return activated;
    }

    public void kill() {
        stop();
        activated.set(false);
    }

    private String storedRepresentation() {
        String wakeUpTime = wakeUpTimeProperty.get().toString();
        String media = mediaProperty.get().toString();
        String activeDays = activeDaysProperty.get().serialize();
        String activated = this.activated.getValue().toString();
        String storedRepresentation = wakeUpTime + separator + media + separator + activeDays
                + separator + activated;
        return storedRepresentation;
    }

    public void storeTo(Writer output) throws IOException {
        output.write(storedRepresentation() + newLine);
    }

}

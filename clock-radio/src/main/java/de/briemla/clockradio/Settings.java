package de.briemla.clockradio;

import java.util.Iterator;
import java.util.List;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import de.briemla.clockradio.controls.AlarmMenu;
import de.briemla.clockradio.controls.DefaultableViewSwitcher;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;

public class Settings {

    private final DefaultableViewSwitcher viewSwitcher;
    private final SimpleBooleanProperty alarmStartedProperty;
    private final ObservableList<Alarm> alarms;
    private final Player player;
    private final PlayerFactory playerFactory;
    private final TimeProvider timeProvider;
    private final AlarmStorage storage;

    public Settings(DefaultableViewSwitcher viewSwitcher, Player player,
            PlayerFactory playerFactory, TimeProvider timeProvider, AlarmStorage storage) {
        this.viewSwitcher = viewSwitcher;
        this.player = player;
        this.playerFactory = playerFactory;
        this.timeProvider = timeProvider;
        this.storage = storage;
        alarmStartedProperty = new SimpleBooleanProperty();
        alarms = FXCollections.observableArrayList();
        alarms.addListener(saveOnChangeTo(storage));
    }

    private ListChangeListener<Alarm> saveOnChangeTo(AlarmStorage storage) {
        return change -> storage.save(alarms);
    }

    public void addAlarm() {
        Alarm alarm = new Alarm(playerFactory, timeProvider, saveToStorage());
        alarms.add(alarm);
        rebindAlarms();
        AlarmMenu alarmMenu = viewSwitcher.show(Alarm.class);
        alarmMenu.setCurrentAlarm(alarm);
    }

    private SaveTrigger saveToStorage() {
        return () -> storage.save(alarms);
    }

    private void rebindAlarms() {
        alarmStartedProperty.unbind();
        if (alarms.isEmpty()) {
            return;
        }
        Iterator<Alarm> alarmIterator = alarms.iterator();
        Alarm nextAlarm = alarmIterator.next();
        ReadOnlyBooleanProperty binding = nextAlarm.alarmStartedProperty();
        for (Alarm alarm : alarms) {
            binding.or(alarm.alarmStartedProperty());
        }
        alarmStartedProperty.bind(binding);
    }

    public ObservableList<Alarm> getAlarms() {
        return alarms;
    }

    public ReadOnlyBooleanProperty defaultVisisbleProperty() {
        return viewSwitcher.defaultVisisbleProperty();
    }

    public void select(Alarm alarm) {
        AlarmMenu alarmSettings = viewSwitcher.show(Alarm.class);
        alarmSettings.setCurrentAlarm(alarm);
    }

    public void delete(Alarm alarm) {
        viewSwitcher.showDefault();
        alarm.kill();
        alarms.remove(alarm);
        rebindAlarms();
    }

    public void stopCurrentAlarm() {
        for (Alarm alarm : alarms) {
            alarm.stop();
        }
        player.stop();
    }

    public List<? extends Station> searchDAB() {
        return player.searchDAB();
    }

    public List<? extends Station> searchFM() {
        return player.searchFM();
    }
}

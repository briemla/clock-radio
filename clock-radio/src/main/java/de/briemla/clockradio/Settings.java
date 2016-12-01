package de.briemla.clockradio;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import de.briemla.clockradio.controls.AlarmMenu;
import de.briemla.clockradio.controls.DefaultableViewSwitcher;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;

public class Settings {

    private final DefaultableViewSwitcher viewSwitcher;
    private final ObservableList<Alarm> alarms;
    private final Player player;
    private final AlarmStorage storage;
    private final AlarmFactory alarmFactory;

    public Settings(DefaultableViewSwitcher viewSwitcher, Player player, AlarmFactory alarmFactory,
            AlarmStorage storage) {
        this.alarmFactory = alarmFactory;
        this.viewSwitcher = viewSwitcher;
        this.player = player;
        this.storage = storage;
        alarms = FXCollections.observableArrayList();
        alarms.addListener(saveOnChangeTo(storage));
        alarmFactory.initialize(() -> storage.save(alarms));
    }

    private ListChangeListener<Alarm> saveOnChangeTo(AlarmStorage storage) {
        return change -> storage.save(alarms);
    }

    public void addAlarm() {
        Alarm alarm = alarmFactory.create();
        alarms.add(alarm);
        AlarmMenu alarmMenu = viewSwitcher.show(Alarm.class);
        alarmMenu.setCurrentAlarm(alarm);
    }

    public ObservableList<Alarm> getAlarms() {
        return alarms;
    }

    public void select(Alarm alarm) {
        AlarmMenu alarmSettings = viewSwitcher.show(Alarm.class);
        alarmSettings.setCurrentAlarm(alarm);
    }

    public void delete(Alarm alarm) {
        viewSwitcher.showDefault();
        alarm.kill();
        alarms.remove(alarm);
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

    public void initializeAlarms() {
        List<Alarm> storedAlarms = storage.load();
        alarms.addAll(storedAlarms);
        ensureAtLeastOneAlarm();
    }

    private void ensureAtLeastOneAlarm() {
        if (alarms.isEmpty()) {
            addAlarm();
        }
    }
}

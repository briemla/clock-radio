package de.briemla.clockradio;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.controls.AlarmMenu;
import de.briemla.clockradio.controls.DefaultableViewSwitcher;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;

public class SettingsTest {

    private static final int first = 0;
    private static final LocalTime nextMinute = LocalTime.of(0, 0);
    private static final WakeUpTime toAnotherTime = new WakeUpTime(12, 34);
    private DefaultableViewSwitcher switcher;
    private Player player;
    private Settings settings;
    private PlayerFactory playerFactory;
    private TimeProvider timeProvider;
    private AlarmStorage storage;
    private AlarmMenu alarmMenu;
    private AlarmFactory alarmFactory;
    private SaveTrigger storageTrigger;

    @Before
    public void initializeMockups() {
        switcher = mock(DefaultableViewSwitcher.class);
        player = mock(Player.class);
        playerFactory = mock(PlayerFactory.class);
        timeProvider = mock(TimeProvider.class);
        storage = mock(AlarmStorage.class);
        alarmMenu = mock(AlarmMenu.class);
        alarmFactory = mock(AlarmFactory.class);
        storageTrigger = mock(SaveTrigger.class);
        settings = new Settings(switcher, player, alarmFactory, storage);
    }

    @Test
    public void searchDAB() throws Exception {
        settings.searchDAB();

        verify(player).searchDAB();
        verifyNoMoreInteractions(player);
        verifyZeroInteractions(switcher);
        verifyZeroInteractions(playerFactory);
    }

    @Test
    public void searchFM() throws Exception {
        settings.searchFM();

        verify(player).searchFM();
        verifyNoMoreInteractions(player);
        verifyZeroInteractions(switcher);
        verifyZeroInteractions(playerFactory);
    }

    @Test
    public void saveNewAlarmWhenAlarmIsAdded() throws Exception {
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
        Alarm factoryAlarm = new Alarm(playerFactory, timeProvider, storageTrigger);
        when(alarmFactory.create()).thenReturn(factoryAlarm);
        when(switcher.show(Alarm.class)).thenReturn(alarmMenu);
        settings.addAlarm();

        verify(storage).save(any());
    }

    @Test
    public void saveWhenWakeUpTimeOfExistingAlarmIsAltered() throws Exception {
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
        Alarm factoryAlarm = new Alarm(playerFactory, timeProvider, storageTrigger);
        when(alarmFactory.create()).thenReturn(factoryAlarm);
        when(switcher.show(Alarm.class)).thenReturn(alarmMenu);
        settings.addAlarm();

        Alarm alarm = settings.getAlarms().get(first);
        alarm.wakeUpTimeProperty().set(toAnotherTime);

        verify(storage).save(any());
        verify(storageTrigger).save();
    }

}

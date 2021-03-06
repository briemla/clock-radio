package de.briemla.clockradio.controls;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Collections;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.AlarmFactory;
import de.briemla.clockradio.AlarmStorage;
import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;

public class MainPanelTest extends GuiTest {

    private Player player;
    private Trigger trigger;
    private TimeProvider timeProvider;
    private SimpleObjectProperty<LocalTime> time;
    private AlarmStorage storage;
    private AlarmFactory alarmFactory;
    private Run run;

    @Test
    public void initializesAlarmTrigger() throws Exception {
        when(storage.load()).thenReturn(Collections.emptyList());

        time.set(LocalTime.of(0, 0, 1));
        verify(trigger).bind(any());
        verify(trigger).startNow();
        verifyNoMoreInteractions(trigger);
        verify(timeProvider, times(2)).timeProperty();
        verify(timeProvider).nextMinute();
        verifyNoMoreInteractions(timeProvider);
        verify(alarmFactory).initialize(any());
        verify(alarmFactory).create();
        verifyNoMoreInteractions(alarmFactory);
        verify(storage).load();
    }

    @Override
    protected Parent getRootNode() {
        player = mock(Player.class);
        trigger = mock(Trigger.class);
        timeProvider = mock(TimeProvider.class);
        run = mock(Run.class);

        LocalTime now = LocalTime.of(0, 0);
        time = new SimpleObjectProperty<>(now);
        when(timeProvider.timeProperty()).thenReturn(time);
        when(timeProvider.nextMinute()).thenReturn(now.plus(1, MINUTES));
        alarmFactory = mock(AlarmFactory.class);
        storage = mock(AlarmStorage.class);
        PlayerFactory playerFactory = mock(PlayerFactory.class);
        Alarm alarm = new Alarm(playerFactory, timeProvider, () -> {});
        when(alarmFactory.create()).thenReturn(alarm);

        return new MainPanel(player, trigger, alarmFactory, timeProvider, storage, run);
    }
}

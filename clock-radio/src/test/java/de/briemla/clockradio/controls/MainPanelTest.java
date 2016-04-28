package de.briemla.clockradio.controls;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.briemla.clockradio.AlarmFactory;
import de.briemla.clockradio.AlarmStorage;
import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.player.Player;

public class MainPanelTest extends GuiTest {

    private Player player;
    private Trigger trigger;
    private TimeProvider timeProvider;
    private SimpleObjectProperty<LocalTime> time;
    private AlarmStorage storage;
    private AlarmFactory alarmFactory;

    @Test
    public void alarmTriggerInitialisation() throws Exception {
        time.set(LocalTime.of(0, 0, 1));
        verify(trigger).bind(any());
        verify(trigger).startNow();
        verifyNoMoreInteractions(trigger);
        verify(timeProvider, times(2)).timeProperty();
        verify(timeProvider).nextMinute();
        verifyNoMoreInteractions(timeProvider);
        verifyZeroInteractions(alarmFactory);
    }

    @Override
    protected Parent getRootNode() {
        player = mock(Player.class);
        trigger = mock(Trigger.class);
        alarmFactory = mock(AlarmFactory.class);
        timeProvider = mock(TimeProvider.class);
        storage = mock(AlarmStorage.class);

        LocalTime now = LocalTime.of(0, 0);
        time = new SimpleObjectProperty<>(now);
        when(timeProvider.timeProperty()).thenReturn(time);
        when(timeProvider.nextMinute()).thenReturn(now.plus(1, MINUTES));
        return new MainPanel(player, trigger, alarmFactory, timeProvider, storage);
    }
}

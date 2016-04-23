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

import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;

public class MainPanelTest extends GuiTest {

    private Player player;
    private Trigger trigger;
    private PlayerFactory playerFactory;
    private TimeProvider timeProvider;
    private SimpleObjectProperty<LocalTime> time;

    @Test
    public void alarmTriggerInitialisation() throws Exception {
        time.set(LocalTime.of(0, 0, 1));
        verify(trigger).bind(any());
        verify(trigger).startNow();
        verifyNoMoreInteractions(trigger);
        verify(timeProvider, times(2)).timeProperty();
        verify(timeProvider).nextMinute();
        verifyNoMoreInteractions(timeProvider);
        verifyZeroInteractions(playerFactory);
    }

    @Override
    protected Parent getRootNode() {
        player = mock(Player.class);
        trigger = mock(Trigger.class);
        playerFactory = mock(PlayerFactory.class);
        timeProvider = mock(TimeProvider.class);
        LocalTime now = LocalTime.of(0, 0);
        time = new SimpleObjectProperty<>(now);
        when(timeProvider.timeProperty()).thenReturn(time);
        when(timeProvider.nextMinute()).thenReturn(now.plus(1, MINUTES));
        return new MainPanel(player, trigger, playerFactory, timeProvider);
    }
}

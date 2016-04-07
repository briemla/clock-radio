package de.briemla.clockradio.controls;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.PlayerFactory;
import de.briemla.clockradio.player.Player;
import de.briemla.utils.FxUtils;

public class MainPanelTest {

    @Before
    public void startFxApplicationThread() throws InterruptedException {
        FxUtils.startFxApplicationThread();
    }

    @Test
    public void alarmTriggerInitialisation() throws Exception {
        Player player = mock(Player.class);
        Trigger trigger = mock(Trigger.class);
        PlayerFactory playerFactory = mock(PlayerFactory.class);
        new MainPanel(player, trigger, playerFactory);

        verify(trigger).bind(any());
        verifyNoMoreInteractions(trigger);
        verifyZeroInteractions(playerFactory);
    }
}
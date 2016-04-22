package de.briemla.clockradio.controls;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;

public class MainPanelTest extends GuiTest {

    private Player player;
    private Trigger trigger;
    private PlayerFactory playerFactory;

    @Test
    public void alarmTriggerInitialisation() throws Exception {

        verify(trigger).bind(any());
        verifyNoMoreInteractions(trigger);
        verifyZeroInteractions(playerFactory);
    }

    @Override
    protected Parent getRootNode() {
        player = mock(Player.class);
        trigger = mock(Trigger.class);
        playerFactory = mock(PlayerFactory.class);
        return new MainPanel(player, trigger, playerFactory);
    }
}

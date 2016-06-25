package de.briemla.clockradio.controls;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javafx.event.Event;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.briemla.clockradio.player.Player;

public class StationSelectorTest extends GuiTest {

    private SearchStation searcher;
    private Player player;
    private StationSelector selector;
    private Event unusedEvent;
    private Run run;

    @Override
    protected Parent getRootNode() {
        searcher = mock(SearchStation.class);
        player = mock(Player.class);
        run = mock(Run.class);
        selector = new StationSelector(searcher, player, run);
        return selector;
    }

    @Test
    public void triggersBackgroundWorkerToRefreshStationList() throws Exception {
        selector.refresh(unusedEvent);

        verify(run).inBackground(any());
    }
}

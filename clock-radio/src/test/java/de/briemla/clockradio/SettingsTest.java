package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.controls.DefaultableViewSwitcher;
import de.briemla.clockradio.player.Player;

public class SettingsTest {

	private DefaultableViewSwitcher switcher;
	private Player player;
	private Settings settings;

	@Before
	public void initializeMockups() {
		switcher = mock(DefaultableViewSwitcher.class);
		player = mock(Player.class);
		settings = new Settings(switcher, player);
	}

	@Test
	public void searchDAB() throws Exception {
		settings.searchDAB();

		verify(player).searchDAB();
		verifyNoMoreInteractions(player);
		verifyZeroInteractions(switcher);
	}

	@Test
	public void searchFM() throws Exception {
		settings.searchFM();

		verify(player).searchFM();
		verifyNoMoreInteractions(player);
		verifyZeroInteractions(switcher);
	}

}

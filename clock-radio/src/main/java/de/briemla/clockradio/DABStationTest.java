package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABService;

public class DABStationTest {

	@Test
	public void tuneTo() throws Exception {
		RadioController controller = mock(RadioController.class);
		Region region = Region.BADEN_WUERTEMBERG;
		DABService service = new DABService(1, "d3d0", "Some name");
		DABChannel channel = new DABChannel(2);
		DABStation station = new DABStation(region, service, channel);

		station.tuneTo(controller);

		verify(controller).switchToDAB();
		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel);
		verify(controller).startDABService(service);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABStation.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}

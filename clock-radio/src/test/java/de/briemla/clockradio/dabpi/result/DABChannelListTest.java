package de.briemla.clockradio.dabpi.result;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.dabpi.command.Region;

public class DABChannelListTest {

	@Test
	public void emptyChannelList() throws Exception {
		Region region = Region.BAYERN;
		DABChannelList channelList = new DABChannelList(region);

		assertThat(channelList.getChannelList(), is(empty()));
	}

	@Test
	public void addSingleFrequency() throws Exception {
		Region region = Region.BAYERN;
		Integer channelId = 1;
		DABChannelList channelList = new DABChannelList(region);

		DABChannel channel = new DABChannel(channelId);
		channelList.add(channel);

		assertThat(channelList.getChannelList(), contains(new DABChannel(channelId)));
	}

	@Test
	public void addMultipleFrequencies() throws Exception {
		Region region = Region.BAYERN;
		DABChannelList channelList = new DABChannelList(region);

		channelList.add(new DABChannel(0));
		channelList.add(new DABChannel(2));

		assertThat(channelList.getChannelList(), containsInAnyOrder(new DABChannel(0), new DABChannel(2)));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABChannelList.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}

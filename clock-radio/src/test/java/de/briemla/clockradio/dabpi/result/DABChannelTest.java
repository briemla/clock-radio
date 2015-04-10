package de.briemla.clockradio.dabpi.result;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class DABChannelTest {

	@Test
	public void serializeChannel0() throws Exception {
		DABChannel channel = new DABChannel(0);

		assertThat(channel.serialize(), is(equalTo("0")));
	}

	@Test
	public void serializeChannel1() throws Exception {
		DABChannel channel = new DABChannel(1);

		assertThat(channel.serialize(), is(equalTo("1")));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABChannel.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}

package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ScanDirectionTest {

	@Test
	public void serializeUp() throws Exception {
		assertThat(ScanDirection.UP.serialize(), is(equalTo("up")));
	}

	@Test
	public void serializeDown() throws Exception {
		assertThat(ScanDirection.DOWN.serialize(), is(equalTo("down")));
	}

}

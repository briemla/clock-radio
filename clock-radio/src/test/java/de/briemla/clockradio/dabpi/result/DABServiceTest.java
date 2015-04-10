package de.briemla.clockradio.dabpi.result;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class DABServiceTest {

	@Test
	public void serializeService0() throws Exception {
		DABService service = new DABService(0, "d3d0", "Some Name");

		assertThat(service.serialize(), is(equalTo("0")));
	}

	@Test
	public void serializeService1() throws Exception {
		DABService service = new DABService(1, "d1d3", "Some other Name");

		assertThat(service.serialize(), is(equalTo("1")));
	}

	@Test
	public void checkCorrectId() throws Exception {
		DABService service = new DABService(0, "d3d0", "Some Name");

		assertThat(service.checkId("d3d0"), is(true));
	}

	@Test
	public void checkAnotherCorrectId() throws Exception {
		DABService service = new DABService(0, "a6d3", "Some other Name");

		assertThat(service.checkId("a6d3"), is(true));
	}

	@Test
	public void checkIncorrectId() throws Exception {
		DABService service = new DABService(0, "a6d3", "Some Name");

		assertThat(service.checkId("d3d0"), is(false));
	}

	@Test
	public void checkAnotherIncorrectId() throws Exception {
		DABService service = new DABService(0, "d3d0", "Some other Name");

		assertThat(service.checkId("a6d3"), is(false));
	}

	@Test
	public void checkCorrectName() throws Exception {
		DABService service = new DABService(0, "d3d0", "Some Name");

		assertThat(service.checkName("Some Name"), is(true));
	}

	@Test
	public void checkAnotherCorrectName() throws Exception {
		DABService service = new DABService(0, "a6d3", "Some other Name");

		assertThat(service.checkName("Some other Name"), is(true));
	}

	@Test
	public void checkIncorrectName() throws Exception {
		DABService service = new DABService(0, "d3d0", "Some Name");

		assertThat(service.checkName("Some other Name"), is(false));
	}

	@Test
	public void checkAnotherIncorrectName() throws Exception {
		DABService service = new DABService(0, "a6d3", "Some other Name");

		assertThat(service.checkName("Some Name"), is(false));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABService.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}

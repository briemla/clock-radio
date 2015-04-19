package de.briemla.clockradio;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class DayGroupTest {

	@Test
	public void empty() throws Exception {
		DayGroup group = new DayGroup();

		assertThat(group.toString(), is(equalTo("")));
	}

	@Test
	public void monday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.MONDAY);

		assertThat(group.toString(), is(equalTo("Mo")));
	}

	@Test
	public void tuesday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.TUESDAY);

		assertThat(group.toString(), is(equalTo("Di")));
	}

	@Test
	public void wednesday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.WEDNESDAY);

		assertThat(group.toString(), is(equalTo("Mi")));
	}

	@Test
	public void thursday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.THURSDAY);

		assertThat(group.toString(), is(equalTo("Do")));
	}

	@Test
	public void friday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.FRIDAY);

		assertThat(group.toString(), is(equalTo("Fr")));
	}

	@Test
	public void saturday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.SATURDAY);

		assertThat(group.toString(), is(equalTo("Sa")));
	}

	@Test
	public void sunday() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.SUNDAY);

		assertThat(group.toString(), is(equalTo("So")));
	}

	@Test
	public void twoDayGroup() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.MONDAY);
		group.add(DayOfWeek.TUESDAY);

		assertThat(group.toString(), is(equalTo("Mo, Di")));
	}

	@Test
	public void anotherTwoDayGroup() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.FRIDAY);
		group.add(DayOfWeek.SATURDAY);

		assertThat(group.toString(), is(equalTo("Fr, Sa")));
	}

	@Test
	public void moreThanTwoDayGroup() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.MONDAY);
		group.add(DayOfWeek.TUESDAY);
		group.add(DayOfWeek.WEDNESDAY);

		assertThat(group.toString(), is(equalTo("Mo - Mi")));
	}

	@Test
	public void anotherMoreThanTwoDayGroup() throws Exception {
		DayGroup group = new DayGroup();
		group.add(DayOfWeek.FRIDAY);
		group.add(DayOfWeek.SATURDAY);
		group.add(DayOfWeek.SUNDAY);

		assertThat(group.toString(), is(equalTo("Fr - So")));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DayGroup.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}

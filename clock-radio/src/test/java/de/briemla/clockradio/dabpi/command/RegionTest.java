package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegionTest {

	@Test
	public void serializeBadenWuertemberg() throws Exception {
		assertThat(Region.BADEN_WUERTEMBERG.serialize(), is(equalTo("0")));
	}

	@Test
	public void serializeBayern() throws Exception {
		assertThat(Region.BAYERN.serialize(), is(equalTo("1")));
	}

	@Test
	public void serializeBerlinBrandenburg() throws Exception {
		assertThat(Region.BERLIN_BRANDENBURG.serialize(), is(equalTo("2")));
	}

	@Test
	public void serializeBremen() throws Exception {
		assertThat(Region.BREMEN.serialize(), is(equalTo("3")));
	}

	@Test
	public void serializeHamburg() throws Exception {
		assertThat(Region.HAMBURG.serialize(), is(equalTo("4")));
	}

	@Test
	public void serializeHessen() throws Exception {
		assertThat(Region.HESSEN.serialize(), is(equalTo("5")));
	}

	@Test
	public void serializeMecklenburgVorpommern() throws Exception {
		assertThat(Region.MECKLENBURG_VORPOMMERN.serialize(), is(equalTo("6")));
	}

	@Test
	public void serializeNiedersachsen() throws Exception {
		assertThat(Region.NIEDERSACHSEN.serialize(), is(equalTo("7")));
	}

	@Test
	public void serializeNordrheinWestfalen() throws Exception {
		assertThat(Region.NORDRHEIN_WESTFALEN.serialize(), is(equalTo("8")));
	}

	@Test
	public void serializeRheinlandPfalz() throws Exception {
		assertThat(Region.RHEINLAND_PFALZ.serialize(), is(equalTo("9")));
	}

	@Test
	public void serializeSaarland() throws Exception {
		assertThat(Region.SAARLAND.serialize(), is(equalTo("10")));
	}

	@Test
	public void serializeSachsen() throws Exception {
		assertThat(Region.SACHSEN.serialize(), is(equalTo("11")));
	}

	@Test
	public void serializeSachsenAnhalt() throws Exception {
		assertThat(Region.SACHSEN_ANHALT.serialize(), is(equalTo("12")));
	}

	@Test
	public void serializeSchleswigHolstein() throws Exception {
		assertThat(Region.SCHLESWIG_HOLSTEIN.serialize(), is(equalTo("13")));
	}

	@Test
	public void serializeThueringen() throws Exception {
		assertThat(Region.THUERINGEN.serialize(), is(equalTo("14")));
	}
}

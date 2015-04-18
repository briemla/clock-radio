package de.briemla.clockradio.controls;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.util.EnumSet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.junit.Test;

import de.briemla.clockradio.ActiveDays;

public class ActiveDaysChangerTest {

	@Test
	public void add() throws Exception {
		ActiveDays expectedDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));
		ObjectProperty<ActiveDays> daysProperty = new SimpleObjectProperty<>(new ActiveDays(
				EnumSet.of(DayOfWeek.TUESDAY)));
		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		ActiveDaysChanger changer = new ActiveDaysChanger(daysProperty, dayOfWeek);

		changer.changed(null, null, true);

		assertThat(daysProperty.get(), is(equalTo(expectedDays)));
	}

	@Test
	public void remove() throws Exception {
		ActiveDays expectedDays = new ActiveDays(EnumSet.of(DayOfWeek.TUESDAY));
		ObjectProperty<ActiveDays> daysProperty = new SimpleObjectProperty<>(new ActiveDays(EnumSet.of(
				DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		ActiveDaysChanger changer = new ActiveDaysChanger(daysProperty, dayOfWeek);

		changer.changed(null, null, false);

		assertThat(daysProperty.get(), is(equalTo(expectedDays)));
	}
}

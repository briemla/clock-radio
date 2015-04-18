package de.briemla.clockradio;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class ActiveDaysTest {

	@Test
	public void nextAlarmOnAllowedDay() throws Exception {
		LocalDateTime day = LocalDateTime.of(0, 1, 1, 0, 0);
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.SATURDAY));

		LocalDateTime nextAlarm = activeDays.nextAlarm(day);

		assertThat(nextAlarm, is(equalTo(day)));
	}

	@Test
	public void nextDayIsAllowed() throws Exception {
		LocalDateTime day = LocalDateTime.of(0, 1, 1, 0, 0);
		LocalDateTime correctedDay = day.plusDays(1);
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.SUNDAY));

		LocalDateTime nextAlarm = activeDays.nextAlarm(day);

		assertThat(nextAlarm, is(equalTo(correctedDay)));
	}

	@Test
	public void dayAfterNextIsAllowed() throws Exception {
		LocalDateTime day = LocalDateTime.of(0, 1, 3, 0, 0);
		LocalDateTime correctedDay = day.plusDays(2);
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.WEDNESDAY));

		LocalDateTime nextAlarm = activeDays.nextAlarm(day);

		assertThat(nextAlarm, is(equalTo(correctedDay)));
	}

	@Test
	public void dayAfterNextIsAllowedWithWeekOverflow() throws Exception {
		LocalDateTime day = LocalDateTime.of(0, 1, 1, 0, 0);
		LocalDateTime correctedDay = day.plusDays(2);
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		LocalDateTime nextAlarm = activeDays.nextAlarm(day);

		assertThat(nextAlarm, is(equalTo(correctedDay)));
	}

	@Test
	public void dayAfterNextIsAllowedWhenMultipleAreAllowed() throws Exception {
		LocalDateTime day = LocalDateTime.of(0, 1, 4, 0, 0);
		LocalDateTime correctedDay = day.plusDays(2);
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.THURSDAY, DayOfWeek.SATURDAY, DayOfWeek.MONDAY));

		LocalDateTime nextAlarm = activeDays.nextAlarm(day);

		assertThat(nextAlarm, is(equalTo(correctedDay)));
	}

	@Test
	public void with() throws Exception {
		ActiveDays extectedActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		ActiveDays withTuesday = activeDays.with(DayOfWeek.TUESDAY);

		assertThat(withTuesday, is(equalTo(extectedActiveDays)));
	}

	@Test
	public void withAlreadyExistingElement() throws Exception {
		ActiveDays extectedActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		ActiveDays withMonday = activeDays.with(DayOfWeek.MONDAY);

		assertThat(withMonday, is(equalTo(extectedActiveDays)));
	}

	@Test
	public void without() throws Exception {
		ActiveDays extectedActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));

		ActiveDays withMonday = activeDays.without(DayOfWeek.TUESDAY);

		assertThat(withMonday, is(equalTo(extectedActiveDays)));
	}

	@Test
	public void withoutAlreadyMissingElement() throws Exception {
		ActiveDays extectedActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		ActiveDays withMonday = activeDays.without(DayOfWeek.TUESDAY);

		assertThat(withMonday, is(equalTo(extectedActiveDays)));
	}

	@Test
	public void withoutResultsInEmptySet() throws Exception {
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
		ActiveDays daily = new ActiveDays(EnumSet.allOf(DayOfWeek.class));
		ActiveDays without = activeDays.without(DayOfWeek.MONDAY);

		assertThat(without, is(equalTo(daily)));
	}

	@Test
	public void newActiveDaysWithEmptyEnumSet() throws Exception {
		ActiveDays activeDays = new ActiveDays(EnumSet.noneOf(DayOfWeek.class));
		ActiveDays daily = new ActiveDays(EnumSet.allOf(DayOfWeek.class));

		assertThat(activeDays, is(equalTo(daily)));
	}

	@Test
	public void contains() throws Exception {
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		assertThat(activeDays.contains(DayOfWeek.MONDAY), is(true));
	}

	@Test
	public void doesNotContain() throws Exception {
		ActiveDays activeDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		assertThat(activeDays.contains(DayOfWeek.TUESDAY), is(false));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(ActiveDays.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
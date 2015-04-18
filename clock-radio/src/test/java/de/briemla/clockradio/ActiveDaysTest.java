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

	@Test
	public void mondayToString() throws Exception {
		ActiveDays monday = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

		assertThat(monday.toString(), is(equalTo("Mo")));
	}

	@Test
	public void tuesdayToString() throws Exception {
		ActiveDays tuesday = new ActiveDays(EnumSet.of(DayOfWeek.TUESDAY));

		assertThat(tuesday.toString(), is(equalTo("Di")));
	}

	@Test
	public void wednesdayToString() throws Exception {
		ActiveDays wednesday = new ActiveDays(EnumSet.of(DayOfWeek.WEDNESDAY));

		assertThat(wednesday.toString(), is(equalTo("Mi")));
	}

	@Test
	public void thursdayToString() throws Exception {
		ActiveDays thursday = new ActiveDays(EnumSet.of(DayOfWeek.THURSDAY));

		assertThat(thursday.toString(), is(equalTo("Do")));
	}

	@Test
	public void fridayToString() throws Exception {
		ActiveDays friday = new ActiveDays(EnumSet.of(DayOfWeek.FRIDAY));

		assertThat(friday.toString(), is(equalTo("Fr")));
	}

	@Test
	public void saturdayToString() throws Exception {
		ActiveDays saturday = new ActiveDays(EnumSet.of(DayOfWeek.SATURDAY));

		assertThat(saturday.toString(), is(equalTo("Sa")));
	}

	@Test
	public void sundayToString() throws Exception {
		ActiveDays sunday = new ActiveDays(EnumSet.of(DayOfWeek.SUNDAY));

		assertThat(sunday.toString(), is(equalTo("So")));
	}

	@Test
	public void twoDayRange() throws Exception {
		ActiveDays range = new ActiveDays(EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));

		assertThat(range.toString(), is(equalTo("Mo, Di")));
	}

	@Test
	public void anotherTwoDayRange() throws Exception {
		ActiveDays range = new ActiveDays(EnumSet.range(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

		assertThat(range.toString(), is(equalTo("Fr, Sa")));
	}
}

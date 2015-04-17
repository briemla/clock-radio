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
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(ActiveDays.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}

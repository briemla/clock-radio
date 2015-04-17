package de.briemla.clockradio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class ActiveDays {

	private final EnumSet<DayOfWeek> days;

	public ActiveDays(EnumSet<DayOfWeek> days) {
		this.days = days;
	}

	public LocalDateTime nextAlarm(LocalDateTime nextTime) {
		DayOfWeek dayOfWeek = nextTime.getDayOfWeek();
		if (days.contains(dayOfWeek)) {
			return nextTime;
		}
		return nextTime.plusDays(numberOfDays(dayOfWeek));
	}

	private long numberOfDays(DayOfWeek dayOfWeek) {
		DayOfWeek nextAllowedDay = nextAllowedDay(dayOfWeek);
		int nextDayValue = nextAllowedDay.getValue();
		int currentDayValue = dayOfWeek.getValue();
		int overflowCorrection = nextDayValue >= currentDayValue ? 0 : 7;
		return nextDayValue - currentDayValue + overflowCorrection;
	}

	private DayOfWeek nextAllowedDay(DayOfWeek dayOfWeek) {
		for (DayOfWeek allowedDay : days) {
			if (allowedDay.compareTo(dayOfWeek) >= 0) {
				return allowedDay;
			}
		}
		return days.iterator().next();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ActiveDays other = (ActiveDays) obj;
		if (days == null) {
			if (other.days != null) {
				return false;
			}
		} else if (!days.equals(other.days)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ActiveDays [days=" + days + "]";
	}

}

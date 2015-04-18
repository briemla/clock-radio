package de.briemla.clockradio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;

public class ActiveDays {

	private final EnumSet<DayOfWeek> days;

	public ActiveDays() {
		this(daily());
	}

	public ActiveDays(EnumSet<DayOfWeek> days) {
		this.days = days.isEmpty() ? daily() : days;
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

	public ActiveDays with(DayOfWeek dayOfWeek) {
		EnumSet<DayOfWeek> newDays = days.clone();
		newDays.add(dayOfWeek);
		return new ActiveDays(newDays);
	}

	public ActiveDays without(DayOfWeek dayOfWeek) {
		EnumSet<DayOfWeek> newDays = days.clone();
		newDays.remove(dayOfWeek);
		if (newDays.isEmpty()) {
			return new ActiveDays();
		}
		return new ActiveDays(newDays);
	}

	private static EnumSet<DayOfWeek> daily() {
		return EnumSet.allOf(DayOfWeek.class);
	}

	public boolean contains(DayOfWeek dayOfWeek) {
		return days.contains(dayOfWeek);
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
		StringBuffer output = new StringBuffer();
		Iterator<DayOfWeek> iterator = days.iterator();
		while (iterator.hasNext()) {
			DayOfWeek dayOfWeek = iterator.next();
			String name = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
			String separator = iterator.hasNext() ? ", " : "";
			output.append(name);
			output.append(separator);
		}
		return output.toString();
	}

}

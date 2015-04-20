package de.briemla.clockradio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class ActiveDays {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String SINGLE_SEPARATOR = ", ";
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
		List<DayGroup> groups = group(days);
		StringBuffer output = new StringBuffer();
		Iterator<DayGroup> iterator = groups.iterator();
		Boolean first = true;
		while (iterator.hasNext()) {
			DayGroup group = iterator.next();
			if (group.isRange() && !first) {
				output.append(LINE_SEPARATOR);
			}
			output.append(group.toString());
			if (iterator.hasNext()) {
				output.append(SINGLE_SEPARATOR);
			}
			first = false;
		}
		return output.toString();
	}

	private List<DayGroup> group(Collection<DayOfWeek> daysOfRange) {
		Iterator<DayOfWeek> iterator = daysOfRange.iterator();
		if (!iterator.hasNext()) {
			return new ArrayList<>();
		}
		ArrayList<DayGroup> groups = new ArrayList<>();
		DayGroup currentGroup = new DayGroup();
		groups.add(currentGroup);
		DayOfWeek current = iterator.next();
		while (true) {
			currentGroup.add(current);
			if (!iterator.hasNext()) {
				break;
			}
			DayOfWeek next = iterator.next();
			int currentValue = current.getValue();
			int nextValue = next.getValue();
			int difference = nextValue - currentValue;
			if (difference > 1) {
				currentGroup = new DayGroup();
				groups.add(currentGroup);
			}
			current = next;
		}
		return groups;
	}

}

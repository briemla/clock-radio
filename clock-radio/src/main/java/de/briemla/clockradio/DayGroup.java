package de.briemla.clockradio;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DayGroup {

	private final ArrayList<DayOfWeek> days;

	public DayGroup() {
		days = new ArrayList<>();
	}

	public void add(DayOfWeek dayOfWeek) {
		days.add(dayOfWeek);
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
		DayGroup other = (DayGroup) obj;
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
		if (days.isEmpty()) {
			return "";
		}
		if (days.size() == 1) {
			return textOf(days.get(0));
		}
		String separator = ", ";
		if (days.size() > 2) {
			separator = " - ";
		}
		return textOf(days.get(0)) + separator + textOf(days.get(days.size() - 1));
	}

	private static String textOf(DayOfWeek dayOfWeek) {
		return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
	}

}

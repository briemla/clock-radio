package de.briemla.clockradio;

public class WakeUpTime {

	private static final String TIME_FORMAT = "%02d:%02d";
	private final Integer hour;
	private final Integer minute;

	public WakeUpTime(Integer hour, Integer minute) {
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
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
		WakeUpTime other = (WakeUpTime) obj;
		if (hour == null) {
			if (other.hour != null) {
				return false;
			}
		} else if (!hour.equals(other.hour)) {
			return false;
		}
		if (minute == null) {
			if (other.minute != null) {
				return false;
			}
		} else if (!minute.equals(other.minute)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(TIME_FORMAT, hour, minute);
	}

}

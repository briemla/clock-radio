package de.briemla.clockradio;

import java.time.LocalDateTime;

public class WakeUpTime {

    private static final int hourIndex = 0;
    private static final int minuteIndex = 1;
    private static final String separator = ":";
    private static final int CORRECTION = 1;
    private static final int ZERO = 0;
    private static final String TIME_FORMAT = "%02d" + separator + "%02d";
    private final Integer hour;
    private final Integer minute;

    public WakeUpTime(Integer hour, Integer minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static WakeUpTime from(String string) {
        String[] values = string.split(separator);
        int hour = hourOf(values);
        int minute = minuteOf(values);
        return new WakeUpTime(hour, minute);
    }

    private static int minuteOf(String[] values) {
        return Integer.parseInt(values[minuteIndex]);
    }

    private static int hourOf(String[] values) {
        return Integer.parseInt(values[hourIndex]);
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

    public LocalDateTime nextAlarm(LocalDateTime now) {
        LocalDateTime alarmDate = now;
        if (now.getHour() > hour || now.getHour() == hour && now.getMinute() >= minute) {
            alarmDate = alarmDate.plusDays(CORRECTION);
        }
        return align(alarmDate.withHour(hour).withMinute(minute));
    }

    private static LocalDateTime align(LocalDateTime time) {
        return time.withSecond(ZERO).withNano(ZERO);
    }

    public WakeUpTime withHour(Integer newHour) {
        return new WakeUpTime(newHour, minute);
    }

    public WakeUpTime withMinute(Integer newMinute) {
        return new WakeUpTime(hour, newMinute);
    }

    // TODO try to find better way instead of getters
    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

}

package de.briemla.clockradio;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class WakeUpTime {

    private static final int timeDigits = 2;
    private static final int hourIndex = 0;
    private static final int minuteIndex = 1;
    private static final String separator = ":";
    private static final int correction = 1;
    private static final int ZERO = 0;
    private static final DateTimeFormatter timeFormat = timeFormatter();

    private final LocalTime time;
    private final Integer hour;
    private final Integer minute;

    public WakeUpTime(Integer hour, Integer minute) {
        this.hour = hour;
        this.minute = minute;
        time = LocalTime.of(hour, minute);
    }

    private static DateTimeFormatter timeFormatter() {
        return new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY, timeDigits)
                                             .appendLiteral(separator)
                                             .appendValue(ChronoField.MINUTE_OF_HOUR, timeDigits)
                                             .toFormatter();
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

    public LocalDateTime nextAlarm(LocalDateTime now) {
        LocalDateTime alarmDate = now.with(time);
        if (now.isAfter(alarmDate) || now.isEqual(alarmDate)) {
            alarmDate = alarmDate.plusDays(correction);
        }
        return align(alarmDate.with(time));
    }

    private static LocalDateTime align(LocalDateTime time) {
        return time.withSecond(ZERO).withNano(ZERO);
    }

    public WakeUpTime withHour(Integer newHour) {
        return new WakeUpTime(newHour, time.getMinute());
    }

    public WakeUpTime withMinute(Integer newMinute) {
        return new WakeUpTime(time.getHour(), newMinute);
    }

    // TODO try to find better way instead of getters
    public int getHour() {
        return time.getHour();
    }

    public int getMinute() {
        return time.getMinute();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hour == null) ? 0 : hour.hashCode());
        result = prime * result + ((minute == null) ? 0 : minute.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        if (time == null) {
            if (other.time != null) {
                return false;
            }
        } else if (!time.equals(other.time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return time.format(timeFormat);
    }
}

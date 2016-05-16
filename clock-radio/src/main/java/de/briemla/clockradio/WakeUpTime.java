package de.briemla.clockradio;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class WakeUpTime {

    private static final int timeDigits = 2;
    private static final String separator = ":";
    private static final int correction = 1;
    private static final int ZERO = 0;
    private static final DateTimeFormatter timeFormat = timeFormatter();

    private final LocalTime time;

    public WakeUpTime(int hour, int minute) {
        time = LocalTime.of(hour, minute);
    }

    private static DateTimeFormatter timeFormatter() {
        return new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY, timeDigits)
                                             .appendLiteral(separator)
                                             .appendValue(ChronoField.MINUTE_OF_HOUR, timeDigits)
                                             .toFormatter();
    }

    public static WakeUpTime from(String string) {
        TemporalAccessor time = timeFormat.parse(string);
        int hour = time.get(HOUR_OF_DAY);
        int minute = time.get(MINUTE_OF_HOUR);
        return new WakeUpTime(hour, minute);
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

package de.briemla.clockradio;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.isHour;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.isMinute;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.isSecond;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.sameDay;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.sameMonthOfYear;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.sameOrBefore;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.sameYear;
import static org.exparity.hamcrest.date.LocalTimeMatchers.after;
import static org.exparity.hamcrest.date.LocalTimeMatchers.before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

import org.junit.Before;
import org.junit.Test;

public class RealTimeProviderTest {

    private static final TemporalAmount timeCorridor = Duration.of(2, MINUTES);
    private TimeProvider timeProvider;

    @Before
    public void initialise() {
        timeProvider = new RealTimeProvider();
    }

    @Test
    public void now() throws Exception {
        LocalDateTime before = LocalDateTime.now();
        LocalDateTime now = timeProvider.now();
        LocalDateTime after = LocalDateTime.now();

        assertThat(now, is(sameOrAfter(before)));
        assertThat(now, is(sameOrBefore(after)));
    }

    @Test
    public void today() throws Exception {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime provided = timeProvider.today();

        assertThat(provided, sameDay(today));
        assertThat(provided, sameMonthOfYear(today));
        assertThat(provided, sameYear(today));
        assertThat(provided, isHour(0));
        assertThat(provided, isMinute(0));
        assertThat(provided, isSecond(0));
    }

    @Test
    public void nextMinute() throws Exception {
        LocalTime before = LocalTime.now();
        LocalTime after = LocalTime.now().plus(timeCorridor);

        LocalTime nextMinute = timeProvider.nextMinute();

        assertThat(nextMinute, is(after(before)));
        assertThat(nextMinute, is(before(after)));
    }

}

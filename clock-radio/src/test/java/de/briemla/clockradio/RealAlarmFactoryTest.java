package de.briemla.clockradio;

import static de.briemla.clockradio.ObservableValueMatchers.hasValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;

public class RealAlarmFactoryTest {

    private static final LocalTime nextMinute = LocalTime.of(0, 0);
    private PlayerFactory playerFactory;
    private TimeProvider timeProvider;
    private SaveTrigger saveTrigger;
    private RealAlarmFactory factory;

    @Before
    public void initialise() {
        playerFactory = mock(PlayerFactory.class);
        timeProvider = mock(TimeProvider.class);
        saveTrigger = mock(SaveTrigger.class);
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
        factory = new RealAlarmFactory(playerFactory, timeProvider);
        factory.initialize(saveTrigger);
    }

    @Test
    public void wiresAlarmTogetherOnCreate() throws Exception {
        Alarm alarm = factory.create();
        alarm.activatedProperty().setValue(false);

        assertThat(alarm, is(not(nullValue())));
        verify(timeProvider).nextMinute();
        verify(saveTrigger).save();
    }

    @Test
    public void wireAlarmTogetherWhenCreatedFromStorage() throws Exception {
        Alarm alarm = factory.fromStorage("12:40;/media/nas/something/;[MONDAY]");
        alarm.activatedProperty().setValue(false);

        assertThat(alarm, is(not(nullValue())));
        WakeUpTime expectedWakeUpTime = new WakeUpTime(12, 40);
        assertThat(alarm.wakeUpTimeProperty(), hasValue(equalTo(expectedWakeUpTime)));
        Media expectedMedia = localFolderFrom("/media/nas/something/");
        assertThat(alarm.mediaProperty(), hasValue(equalTo(expectedMedia)));
        ActiveDays expectedActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
        assertThat(alarm.activeDaysProperty(), hasValue(equalTo(expectedActiveDays)));
        verify(timeProvider).nextMinute();
    }

    private LocalFolder localFolderFrom(String pathname) {
        return new LocalFolder(new File(pathname).toPath());
    }
}

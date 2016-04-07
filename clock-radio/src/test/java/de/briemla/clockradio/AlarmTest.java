package de.briemla.clockradio;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class AlarmTest {

    private PlayerFactory player;
    private PlayerWorker worker;

    @Before
    public void initialise() {
        player = mock(PlayerFactory.class);
        worker = mock(PlayerWorker.class);
        when(player.create(any(Media.class))).thenReturn(worker);
    }

    @Test
    public void playMusicInBackground() throws Exception {
        Alarm alarm = new Alarm(player);

        boolean playing = alarm.play(playAlwaysTime());

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
    }

    private static LocalDateTime playAlwaysTime() {
        return playTime().plusDays(1);
    }

    @Test
    public void playAndStopMusic() throws Exception {
        Alarm alarm = new Alarm(player);

        boolean playing = alarm.play(playAlwaysTime());
        alarm.stop();

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
        verify(worker).stop();
    }

    @Test
    public void playAtCorrectTime() throws Exception {
        Alarm running = new Alarm(player);
        running.wakeUpTimeProperty()
               .set(new WakeUpTime(0, 2));

        LocalDateTime atTime = playTime();
        boolean playing = running.play(atTime);

        assertThat(playing, is(false));
        verifyZeroInteractions(player);
    }

    private static LocalDateTime playTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 1));
    }
}

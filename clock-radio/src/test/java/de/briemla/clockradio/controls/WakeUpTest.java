package de.briemla.clockradio.controls;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Parent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.briemla.clockradio.AlarmStorage;
import de.briemla.clockradio.AlarmTrigger;
import de.briemla.clockradio.RealAlarmFactory;
import de.briemla.clockradio.SaveTrigger;
import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.RealPlayerFactory;

public class WakeUpTest extends GuiTest {

    private static final class MockedPlayer implements Player {
        private boolean searchFmCalled;
        private boolean searchDabCalled;
        private boolean playCalled;
        private boolean stopCalled;

        public MockedPlayer() {
            super();
            reset();
        }

        @Override
        public void stop() {
            stopCalled = true;
        }

        @Override
        public List<FMStation> searchFM() {
            searchFmCalled = true;
            return Collections.emptyList();
        }

        @Override
        public List<DABStation> searchDAB() {
            searchDabCalled = true;
            return Collections.emptyList();
        }

        @Override
        public ReadOnlyBooleanProperty playingProperty() {
            throw new RuntimeException("Method accidently called in test");
        }

        @Override
        public void play(Station station) throws IOException {
            throw new RuntimeException("Method accidently called in test");
        }

        @Override
        public void play(URI uriToPlay) {
            playCalled = true;
        }

        public void reset() {
            searchFmCalled = false;
            searchDabCalled = false;
            playCalled = false;
            stopCalled = false;
        }

    }

    private static final LocalDateTime initialDateTime = LocalDateTime.of(0, 1, 1, 0, 0);
    private static final LocalDateTime startOfFirstDay = LocalDateTime.of(0, 1, 1, 0, 0);
    private static final LocalDateTime startOfSecondDay = LocalDateTime.of(0, 1, 2, 0, 0);
    private static final LocalTime initialTime = LocalTime.of(0, 0);
    private static final LocalTime wakeUpTime = LocalTime.of(0, 1);
    private static final LocalDateTime firstWakeUpDate = LocalDateTime.of(0, 1, 1, 0, 1);
    private static final LocalDateTime secondWakeUpDate = LocalDateTime.of(0, 1, 2, 0, 1);
    private static final int timeout = (int) Duration.of(10, SECONDS).getSeconds();
    private static final LocalTime triggerAlarm = LocalTime.of(0, 1, 0);
    private static final LocalTime afterAlarm = LocalTime.of(0, 2, 0);
    private MockedPlayer player;
    private Trigger trigger;
    private PlayerFactory playerFactory;
    private TimeProvider timeProvider;
    private SimpleObjectProperty<LocalTime> time;
    private RealAlarmFactory alarmFactory;

    @Test
    public void whenCancelledAlarmIsTriggeredAgain() throws Exception {
        // TODO This test expects that the default local folder contains mp3 media. Make it testable
        when(timeProvider.today()).thenReturn(startOfFirstDay);
        when(timeProvider.now()).thenReturn(firstWakeUpDate);
        updateTime(triggerAlarm);
        assertThat(player.searchFmCalled, is(true));
        assertThat(player.searchDabCalled, is(true));
        waitUntil(player, hasBeenPlayed(), timeout);

        Node stopSound = find("#stopSound");
        click(stopSound);
        waitUntil(player, hasBeenStoped(), timeout);
        player.reset();
        updateTime(afterAlarm);

        when(timeProvider.today()).thenReturn(startOfSecondDay);
        when(timeProvider.now()).thenReturn(secondWakeUpDate);
        updateTime(triggerAlarm);
        waitUntil(player, hasBeenPlayed(), timeout);

        verify(timeProvider, atLeastOnce()).timeProperty();
        verify(timeProvider).nextMinute();
        verify(timeProvider, times(3)).now();
        verify(timeProvider, times(2)).today();
        verifyNoMoreInteractions(timeProvider);
    }

    private void updateTime(LocalTime next) throws InterruptedException {
        CountDownLatch waitForUpdate = new CountDownLatch(1);
        Platform.runLater(() -> {
            time.set(next);
            waitForUpdate.countDown();
        });
        waitForUpdate.await();
    }

    @Override
    protected Parent getRootNode() {
        timeProvider = mock(TimeProvider.class);
        time = new SimpleObjectProperty<>(initialTime);
        when(timeProvider.timeProperty()).thenReturn(time);
        when(timeProvider.nextMinute()).thenReturn(wakeUpTime);
        when(timeProvider.now()).thenReturn(initialDateTime);
        when(timeProvider.today()).thenReturn(startOfFirstDay);
        player = new MockedPlayer();
        trigger = new AlarmTrigger(timeProvider);
        playerFactory = new RealPlayerFactory(player);
        alarmFactory = new RealAlarmFactory(playerFactory, timeProvider);
        SaveTrigger saveTrigger = mock(SaveTrigger.class);
        alarmFactory.initialize(saveTrigger);
        AlarmStorage storage = mock(AlarmStorage.class);
        return new MainPanel(player, trigger, alarmFactory, timeProvider, storage);
    }

    private static Matcher<MockedPlayer> hasBeenPlayed() {
        return new TypeSafeMatcher<WakeUpTest.MockedPlayer>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Player has been played");
            }

            @Override
            protected boolean matchesSafely(MockedPlayer item) {
                return item.playCalled;
            }

            @Override
            protected void describeMismatchSafely(MockedPlayer item,
                    Description mismatchDescription) {
                mismatchDescription.appendText("Player has not been played");
            }
        };
    }

    private static Matcher<MockedPlayer> hasBeenStoped() {
        return new TypeSafeMatcher<WakeUpTest.MockedPlayer>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Player has been stopped");
            }

            @Override
            protected boolean matchesSafely(MockedPlayer item) {
                return item.stopCalled;
            }

            @Override
            protected void describeMismatchSafely(MockedPlayer item,
                    Description mismatchDescription) {
                mismatchDescription.appendText("Player has not been stopped");
            }
        };
    }

}

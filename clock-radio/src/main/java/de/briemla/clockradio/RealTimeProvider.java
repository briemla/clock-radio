package de.briemla.clockradio;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

public class RealTimeProvider implements TimeProvider {

    private static final LocalTime startOfTheDay = LocalTime.of(0, 0);
    private final SimpleObjectProperty<LocalTime> timeProperty;

    public RealTimeProvider() {
        super();
        timeProperty = new SimpleObjectProperty<>();
        updateTime();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.playFromStart();
    }

    @Override
    public ReadOnlyObjectProperty<LocalTime> timeProperty() {
        return timeProperty;
    }

    private void updateTime() {
        LocalTime now = currentMinute();
        if (now.equals(timeProperty.get())) {
            return;
        }
        timeProperty.set(now);
    }

    private LocalTime currentMinute() {
        return LocalTime.now().withSecond(0).withNano(0);
    }

    @Override
    public LocalTime nextMinute() {
        return currentMinute().plusMinutes(1);
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime today() {
        return now().with(startOfTheDay);
    }

}

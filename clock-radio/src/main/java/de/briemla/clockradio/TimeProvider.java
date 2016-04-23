package de.briemla.clockradio;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

public class TimeProvider {

    private static final LocalTime startOfTheDay = LocalTime.of(0, 0);
    private final Timeline timeline;
    private final SimpleObjectProperty<LocalTime> timeProperty;

    public TimeProvider() {
        super();
        timeProperty = new SimpleObjectProperty<>();
        updateTime();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.playFromStart();
    }

    public ReadOnlyObjectProperty<LocalTime> timeProperty() {
        return timeProperty;
    }

    private void updateTime() {
        timeProperty.set(LocalTime.now());
    }

    public LocalTime nextMinute() {
        return LocalTime.now().plusMinutes(1);
    }

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public LocalDateTime today() {
        return now().with(startOfTheDay);
    }

}

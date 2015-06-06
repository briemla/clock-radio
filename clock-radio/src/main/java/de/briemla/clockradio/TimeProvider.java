package de.briemla.clockradio;

import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class TimeProvider {
    private final Timeline timeline;
    private final SimpleObjectProperty<LocalTime> timeProperty;

    public TimeProvider() {
        super();
        timeProperty = new SimpleObjectProperty<>();
        updateTime();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                updateTime();
            }
        }));
        timeline.playFromStart();
    }

    public ReadOnlyObjectProperty<LocalTime> timeProperty() {
        return timeProperty;
    }

    private void updateTime() {
        timeProperty.set(LocalTime.now());
    }

}

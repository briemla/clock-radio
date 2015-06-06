package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import jfxtras.scene.layout.CircularPane;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.WakeUpTime;

public class TimeEditor extends AnchorPane {

    @FXML
    private Label time;
    @FXML
    private StackPane hourParent;
    @FXML
    private StackPane minuteParent;
    @FXML
    private CircularPane morning;
    @FXML
    private CircularPane afternoon;
    @FXML
    private CircularPane minuteCircle;
    @FXML
    private Pointer2Circle hourPointer;
    @FXML
    private Pointer minutePointer;

    private final SimpleObjectProperty<WakeUpTime> timeProperty;

    public TimeEditor() {
        super();
        FXUtil.load(this, this);

        timeProperty = new SimpleObjectProperty<>(new WakeUpTime(0, 0));
        for (int currentHour = 0; currentHour <= 11; currentHour++) {
            morning.add(timeLabelForHour(currentHour));
        }
        for (int currentHour = 12; currentHour <= 23; currentHour++) {
            afternoon.add(timeLabelForHour(currentHour));
        }
        for (int currentMinute = 1; currentMinute <= 12; currentMinute++) {
            minuteCircle.add(timeLabelForMinute(currentMinute * 5 % 60));
        }

        hourPointer.longLengthValue().bind(afternoon.diameterProperty());
        hourPointer.lengthValue().bind(morning.diameterProperty());
        minutePointer.lengthValue().bind(minuteCircle.diameterProperty());

        timeProperty.addListener((change, oldValue, newValue) -> {
            hourPointer.valueProperty().set(newValue.getHour());
            minutePointer.valueProperty().set(newValue.getMinute());
        });

        hourParent.addEventHandler(MouseEvent.ANY, event -> {
            if (event.isPrimaryButtonDown()) {
                double x = event.getX();
                double y = event.getY();
                double width = hourParent.getWidth();
                double height = hourParent.getHeight();
                Integer newHour = Angle.toHour(x, y, width, height);
                timeProperty.set(timeProperty.get().withHour(newHour));
                event.consume();
            }
        });
        minuteParent.addEventHandler(MouseEvent.ANY, event -> {
            if (event.isPrimaryButtonDown()) {
                double x = event.getX();
                double y = event.getY();
                double width = minuteParent.getWidth();
                double height = minuteParent.getHeight();
                int newMinute = Angle.toMinute(x, y, width, height);
                timeProperty.set(timeProperty.get().withMinute(newMinute));
                event.consume();
            }
        });

        time.textProperty().bind(timeProperty.asString());
    }

    private static Label timeLabelForHour(int hour) {
        Label label = new Label(String.valueOf(hour));
        label.setId("hour" + hour);
        return label;
    }

    private static Label timeLabelForMinute(int minute) {
        Label label = new Label(String.valueOf(minute));
        label.setId("minute" + minute);
        return label;
    }

    public ObjectProperty<WakeUpTime> timeProperty() {
        return timeProperty;
    }
}

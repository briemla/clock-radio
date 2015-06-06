package de.briemla.clockradio.controls;

import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.TimeProvider;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DigitalClock extends StackPane {

    @FXML
    private Label time;

    private final TimeProvider timeProvider;

    public DigitalClock() {
        super();
        FXUtil.load(this, this);
        timeProvider = new TimeProvider();
        time.textProperty().bind(providedTime());
    }

    private StringBinding providedTime() {
        return timeProvider.timeProperty().asString("%1$TH:%1$TM");
    }

}

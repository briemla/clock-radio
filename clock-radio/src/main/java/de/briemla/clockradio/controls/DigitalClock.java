package de.briemla.clockradio.controls;

import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import de.briemla.clockradio.FxUtil;
import de.briemla.clockradio.TimeProvider;

public class DigitalClock extends StackPane {

    @FXML
    private Label time;

    private final TimeProvider timeProvider;

    public DigitalClock(TimeProvider timeProvider) {
        super();
        FxUtil.load(this, this);
        this.timeProvider = timeProvider;
        time.textProperty().bind(providedTime());
    }

    private StringBinding providedTime() {
        return timeProvider.timeProperty().asString("%1$TH:%1$TM");
    }

}

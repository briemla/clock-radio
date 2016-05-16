package de.briemla.clockradio.controls;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FxUtil;
import de.briemla.clockradio.Settings;

public class AlarmCell extends AnchorPane {

    @FXML
    private AnchorPane container;
    @FXML
    private ToggleButton active;
    @FXML
    private Label time;
    @FXML
    private Label weekdays;
    @FXML
    private Label mediaDescription;
    @FXML
    private Button settings;

    private final BooleanProperty activeProperty;

    public AlarmCell(Alarm alarm, Settings settings) {
        super();
        FxUtil.load(this, this);
        activeProperty = new ActivePseudoClassProperty(this);
        // TODO maybe create timeProperty in Alarm class
        time.textProperty().bind(alarm.wakeUpTimeProperty().asString());
        mediaDescription.textProperty().bind(alarm.mediaProperty().asString());
        weekdays.textProperty().bind(alarm.activeDaysProperty().asString());
        activeProperty.bindBidirectional(alarm.activatedProperty());
        active.selectedProperty().bindBidirectional(activeProperty);
        setOnMouseClicked(event -> settings.select(alarm));
    }

}

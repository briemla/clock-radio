package de.briemla.clockradio.controls;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Settings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AlarmCell extends AnchorPane {

	@FXML
	private AnchorPane container;
	@FXML
	private CheckBox active;
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
		FXUtil.load(this, this);
		activeProperty = new ActivePseudoClassProperty(this);
		// TODO maybe create timeProperty in Alarm class
		time.textProperty().bind(alarm.wakeUpTimeProperty().asString());
		mediaDescription.textProperty().bind(alarm.mediaProperty().asString());
		activeProperty.bindBidirectional(alarm.activatedProperty());
		active.selectedProperty().bindBidirectional(activeProperty);
		setOnMouseClicked(event -> settings.select(alarm));
	}

	public void unbind() {
		time.textProperty().unbind();
		weekdays.textProperty().unbind();
		mediaDescription.textProperty().unbind();
	}

	public BooleanProperty activatedProperty() {
		return activeProperty;
	}
}
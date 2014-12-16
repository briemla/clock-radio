package de.briemla.clockradio;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AlarmCell extends AnchorPane {

	private static final String TIME_FORMAT = "%02d";
	@FXML
	private AnchorPane container;
	@FXML
	private Label time;
	@FXML
	private Label weekdays;
	@FXML
	private Label mediaDescription;
	@FXML
	private Button settings;

	private final BooleanProperty active;

	public AlarmCell(Alarm alarm, Settings settings) {
		super();
		FXUtil.load(this, this);
		active = new ActivePseudoClassProperty(this);
		// TODO maybe create timeProperty in Alarm class
		time.textProperty().bind(alarm.hourProperty().asString(TIME_FORMAT).concat(":").concat(alarm.minuteProperty().asString(TIME_FORMAT)));
		mediaDescription.textProperty().bind(alarm.mediaProperty().asString());
		active.bindBidirectional(alarm.activatedProperty());
		setOnMouseClicked(event -> active.set(!active.get()));
		this.settings.setOnAction(event -> settings.select(alarm));
	}

	public void unbind() {
		time.textProperty().unbind();
		weekdays.textProperty().unbind();
		mediaDescription.textProperty().unbind();
	}

	public BooleanProperty activatedProperty() {
		return active;
	}
}

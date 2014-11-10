package de.briemla.clockradio;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AlarmCell extends AnchorPane {

	@FXML
	private AnchorPane container;
	@FXML
	private Label time;
	@FXML
	private Button settings;

	private final BooleanProperty activated;

	public AlarmCell(Alarm alarm, Settings settings) {
		super();
		FXUtil.load(this, this);
		// TODO maybe create timeProperty in Alarm class
		time.textProperty().bind(alarm.hourProperty().asString().concat(":").concat(alarm.minuteProperty().asString()));
		activated = new ActivatedPseudoClassProperty(this);
		setOnMouseClicked(event -> activated.set(!activated.get()));
		this.settings.setOnAction(event -> settings.select(alarm));
		getStyleClass().add("selectable-label");
	}

	public void unbind() {
		time.textProperty().unbind();
	}

	public BooleanProperty activatedProperty() {
		return activated;
	}
}

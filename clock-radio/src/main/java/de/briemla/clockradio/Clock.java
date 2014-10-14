package de.briemla.clockradio;

import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Clock extends StackPane {

	@FXML
	private Label time;

	private final TimeProvider timeProvider;

	public Clock(TimeProvider timeProvider) {
		super();
		FXUtil.load(this, this);
		this.timeProvider = timeProvider;
		time.textProperty().bind(providedTime());
	}

	private StringBinding providedTime() {
		return timeProvider.timeProperty().asString("%1$TH:%1$TM:%1$TS");
	}

}

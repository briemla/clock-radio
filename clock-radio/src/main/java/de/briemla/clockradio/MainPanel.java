package de.briemla.clockradio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainPanel extends VBox {

	@FXML
	private Clock clock;
	@FXML
	private HBox alarm;

	public MainPanel() {
		super();
		FXUtil.load(this, this);
	}

	void startTimeline() {
		clock.startTimeline();
	}

	@FXML
	public void openSettings(ActionEvent event) {

	}

}

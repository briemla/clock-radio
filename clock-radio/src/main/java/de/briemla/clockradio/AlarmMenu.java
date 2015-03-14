package de.briemla.clockradio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AlarmMenu extends AnchorPane {

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

	public AlarmMenu() {
		super();
		FXUtil.load(this, this);
	}

	public void unbind() {
		time.textProperty().unbind();
		weekdays.textProperty().unbind();
		mediaDescription.textProperty().unbind();
	}

}

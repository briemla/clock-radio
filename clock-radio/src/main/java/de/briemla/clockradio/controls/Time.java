package de.briemla.clockradio.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import jfxtras.scene.layout.CircularPane;
import de.briemla.clockradio.FXUtil;

public class Time extends AnchorPane {

	@FXML
	private Label hour;
	@FXML
	private Label minute;
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
	private final ToggleGroup hourToggle;
	private final ToggleGroup minuteToggle;

	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;

	public Time() {
		super();
		FXUtil.load(this, this);
		hourToggle = new ToggleGroup();
		minuteToggle = new ToggleGroup();
		for (int currentHour = 1; currentHour <= 12; currentHour++) {
			ToggleButton toggleButton = createToggleButton(currentHour, hourToggle);
			morning.add(toggleButton);
		}
		for (int currentHour = 13; currentHour <= 24; currentHour++) {
			ToggleButton toggleButton = createToggleButton(currentHour % 24, hourToggle);
			afternoon.add(toggleButton);
		}
		for (int currentMinute = 1; currentMinute <= 12; currentMinute++) {
			ToggleButton toggleButton = createToggleButton(currentMinute * 5 % 60, minuteToggle);
			minuteCircle.add(toggleButton);
		}

		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();

		hour.textProperty().bind(hourProperty.asString("%02d"));
		minute.textProperty().bind(minuteProperty.asString("%02d"));

		hourToggle.selectedToggleProperty().addListener((observable, oldValu, newValue) -> hourProperty.set((int) newValue.getUserData()));
		minuteToggle.selectedToggleProperty().addListener((observable, oldValu, newValue) -> minuteProperty.set((int) newValue.getUserData()));

		hour.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToHour());
		minute.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToMinute());
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}

	private ToggleButton createToggleButton(int currentHour, ToggleGroup toggleGroup) {
		ToggleButton toggleButton = new ToggleButton(String.valueOf(currentHour));
		toggleButton.setToggleGroup(toggleGroup);
		toggleButton.setUserData(currentHour);
		toggleButton.setOnAction(event -> switchToMinute());
		return toggleButton;
	}

	private void switchToMinute() {
		hourParent.setVisible(false);
		minuteParent.setVisible(true);
	}

	private void switchToHour() {
		hourParent.setVisible(true);
		minuteParent.setVisible(false);
	}
}

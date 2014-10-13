package de.briemla.clockradio;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;

public class AlarmView extends HBox {

	@FXML
	private ListView<Alarm> alarm;

	private Settings settings;

	public AlarmView() {
		super();
		FXUtil.load(this, this);
		alarm.setCellFactory(new AlarmCellFactory());
		alarm.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	public void setSettings(Settings settings) {
		if (this.settings != null) {
			Bindings.unbindContentBidirectional(alarm.getItems(), this.settings.getAlarms());
		}
		this.settings = settings;
		Bindings.bindContentBidirectional(alarm.getItems(), this.settings.getAlarms());
		this.settings.setSelectedAlarms(alarm.getSelectionModel().getSelectedItems());
		this.settings.defaultVisisbleProperty().addListener(new DefaultVisisbleListener(alarm));
	}

	public void add(ActionEvent event) {
		if (settings == null) {
			throw new NullPointerException("Settings have not been initialized in AlarmView");
		}

		settings.addAlarm();
	}

}

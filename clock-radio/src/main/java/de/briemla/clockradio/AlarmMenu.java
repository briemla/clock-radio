package de.briemla.clockradio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AlarmMenu extends VBox {

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
	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;
	private final ViewSwitcher viewSwitcher;

	public AlarmMenu(ViewSwitcher viewSwitcher) {
		super();
		this.viewSwitcher = viewSwitcher;
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
		time.textProperty().bind(hourProperty.asString().concat(":").concat(minuteProperty.asString()));
	}

	public void unbind() {
		hourProperty.unbind();
		minuteProperty.unbind();
		mediaProperty.unbind();
	}

	public void setCurrentAlarm(Alarm alarm) {
		unbind();
		bindTo(alarm);
	}

	private void bindTo(Alarm alarm) {
		hourProperty.bindBidirectional(alarm.hourProperty());
		minuteProperty.bindBidirectional(alarm.minuteProperty());
		mediaProperty.bindBidirectional(alarm.mediaProperty());
	}

}

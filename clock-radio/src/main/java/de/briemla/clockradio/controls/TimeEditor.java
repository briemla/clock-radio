package de.briemla.clockradio.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import jfxtras.scene.layout.CircularPane;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.WakeUpTime;

public class TimeEditor extends AnchorPane {

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
	@FXML
	private Pointer2Circle hourPointer;
	@FXML
	private Pointer minutePointer;

	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;
	private final SimpleObjectProperty<WakeUpTime> timeProperty;

	public TimeEditor() {
		super();
		FXUtil.load(this, this);

		timeProperty = new SimpleObjectProperty<>();
		hourProperty = new SimpleIntegerProperty(0);
		minuteProperty = new SimpleIntegerProperty(0);
		for (int currentHour = 0; currentHour <= 11; currentHour++) {
			morning.add(timeLabelFor(currentHour));
		}
		for (int currentHour = 12; currentHour <= 23; currentHour++) {
			afternoon.add(timeLabelFor(currentHour));
		}
		for (int currentMinute = 1; currentMinute <= 12; currentMinute++) {
			minuteCircle.add(timeLabelFor(currentMinute * 5 % 60));
		}

		hourPointer.valueProperty().bind(hourProperty);
		hourPointer.longLengthValue().bind(afternoon.diameterProperty());
		hourPointer.lengthValue().bind(morning.diameterProperty());
		minutePointer.valueProperty().bind(minuteProperty);
		minutePointer.lengthValue().bind(minuteCircle.diameterProperty());

		hourParent.addEventHandler(MouseEvent.ANY, event -> {
			if (event.isPrimaryButtonDown()) {
				double x = event.getX();
				double y = event.getY();
				double width = hourParent.getWidth();
				double height = hourParent.getHeight();
				hourProperty.set(Angle.toHour(x, y, width, height));
				event.consume();
			}
		});
		minuteParent.addEventHandler(MouseEvent.ANY, event -> {
			if (event.isPrimaryButtonDown()) {
				double x = event.getX();
				double y = event.getY();
				double width = minuteParent.getWidth();
				double height = minuteParent.getHeight();
				minuteProperty.set(Angle.toMinute(x, y, width, height));
				event.consume();
			}
		});

		hour.textProperty().bind(hourProperty.asString("%02d"));
		minute.textProperty().bind(minuteProperty.asString("%02d"));

		hour.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToHour());
		minute.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToMinute());
	}

	private static Label timeLabelFor(int time) {
		Label label = new Label(String.valueOf(time));
		label.setId(String.valueOf(time));
		return label;
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}

	private void switchToMinute() {
		hourParent.setVisible(false);
		minuteParent.setVisible(true);
	}

	public void switchToHour() {
		hourParent.setVisible(true);
		minuteParent.setVisible(false);
	}

	public ObjectProperty<WakeUpTime> timeProperty() {
		return timeProperty;
	}
}
package de.briemla.clockradio.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
	// @FXML
	// private Pointer pointer;
	@FXML
	private Circle circle;
	@FXML
	private Line line;

	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;

	public Time() {
		super();
		FXUtil.load(this, this);

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

		line.startXProperty().bind(hourParent.widthProperty().divide(2d));
		line.startYProperty().bind(hourParent.heightProperty().divide(2d));
		hourProperty.addListener(change -> updateHourPointer());
		hourParent.widthProperty().addListener(change -> updateHourPointer());
		hourParent.heightProperty().addListener(change -> updateHourPointer());

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

		hour.textProperty().bind(hourProperty.asString("%02d"));
		minute.textProperty().bind(minuteProperty.asString("%02d"));

		hour.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToHour());
		minute.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchToMinute());
	}

	private void updateHourPointer() {
		double width = hourParent.getWidth();
		double height = hourParent.getHeight();
		double centerX = width / 2.0d;
		double centerY = height / 2.0d;
		Point2D center = new Point2D(centerX, centerY);
		int toHour = hourProperty.get();
		double distanceToCenter = distanceTo(toHour);
		Point2D currentHourAligned = align(center, toHour);
		Point2D newCoordinates = toCircle(currentHourAligned, center, distanceToCenter);
		circle.setCenterX(newCoordinates.getX());
		circle.setCenterY(newCoordinates.getY());
		line.setEndX(newCoordinates.getX());
		line.setEndY(newCoordinates.getY());
	}

	private static Point2D align(Point2D center, int currentHour) {
		double hourAlignedX = center.getX() + Math.cos(Math.toRadians(currentHour * 30d - 90d));
		double hourAlignedY = center.getY() + Math.sin(Math.toRadians(currentHour * 30d - 90d));
		return new Point2D(hourAlignedX, hourAlignedY);
	}

	private double distanceTo(int toHour) {
		double morningDistance = morning.getDiameter() / 2d;
		double afternoonDistance = afternoon.getDiameter() / 2d;
		return (toHour >= 12 ? afternoonDistance : morningDistance) - 10d;
	}

	private static Point2D toCircle(Point2D current, Point2D center, double suggestedLength) {
		Point2D relative = current.subtract(center);
		double distance = relative.magnitude();
		double factor = distance == 0d ? suggestedLength : suggestedLength / distance;
		return relative.multiply(factor).add(center);
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

	private void switchToHour() {
		hourParent.setVisible(true);
		minuteParent.setVisible(false);
	}
}

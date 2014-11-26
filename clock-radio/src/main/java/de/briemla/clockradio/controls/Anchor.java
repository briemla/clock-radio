package de.briemla.clockradio.controls;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

class Anchor extends Circle {
	Anchor(Color color, DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 10);
		setFill(color.deriveColor(1, 1, 1, 0.5));
		setStroke(color);
		setStrokeWidth(1);
		setStrokeType(StrokeType.OUTSIDE);

		x.bind(centerXProperty());
		y.bind(centerYProperty());
		// enableDrag();
	}

	// make a node movable by dragging it around with the mouse.
	private void enableDrag() {
		setOnMousePressed(mouseEvent -> {
			// record a delta distance for the drag and drop operation.
			getScene().setCursor(Cursor.MOVE);
		});
		setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
		setOnMouseDragged(mouseEvent -> {
			double centerX = getScene().getWidth() / 2d;
			double centerY = getScene().getHeight() / 2d;
			double relativeX = mouseEvent.getX() - centerX;
			double relativeY = getScene().getHeight() - mouseEvent.getY() - centerY;

			double angle = Math.atan2(relativeY, relativeX);
			double newX = 50d * Math.cos(angle) + centerX;
			double newY = getScene().getHeight() - centerY - 50d * Math.sin(angle);

			if (newX > 0 && newX < getScene().getWidth()) {
				setCenterX(newX);
			}
			if (newY > 0 && newY < getScene().getHeight()) {
				setCenterY(newY);
			}
		});
		setOnMouseEntered(mouseEvent -> {
			if (!mouseEvent.isPrimaryButtonDown()) {
				getScene().setCursor(Cursor.HAND);
			}
		});
		setOnMouseExited(mouseEvent -> {
			if (!mouseEvent.isPrimaryButtonDown()) {
				getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}
}
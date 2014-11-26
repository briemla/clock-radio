package de.briemla.clockradio.controls;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

class BoundLine extends Line {
	BoundLine(double startX, double startY, double endX, double endY, Color color) {
		super(startX, startY, endX, endY);
		setStrokeWidth(2);
		setStroke(color.deriveColor(0, 1, 1, 0.5));
		setStrokeLineCap(StrokeLineCap.BUTT);
		setMouseTransparent(true);
	}
}
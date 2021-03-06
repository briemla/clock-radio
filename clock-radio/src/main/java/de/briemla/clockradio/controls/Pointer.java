package de.briemla.clockradio.controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import de.briemla.clockradio.FxUtil;

public class Pointer extends AnchorPane {

    private static final double POINTER_DISTANCE_OFFSET = 18d;
    private static final double TIC_ANGLE = 2 * Math.PI / 60;
    private static final double OFFSET = Math.PI / 2d;
    @FXML
    private Circle circle;
    @FXML
    private Line line;

    private final SimpleIntegerProperty value = new SimpleIntegerProperty();
    private final SimpleDoubleProperty length = new SimpleDoubleProperty();

    public Pointer() {
        super();
        FxUtil.load(this, this);

        line.startXProperty().bind(widthProperty().divide(2d));
        line.startYProperty().bind(heightProperty().divide(2d));
        widthProperty().addListener(change -> updateHourPointer());
        heightProperty().addListener(change -> updateHourPointer());
        value.addListener(change -> updateHourPointer());
    }

    private void updateHourPointer() {
        double width = getWidth();
        double height = getHeight();
        double centerX = width / 2.0d;
        double centerY = height / 2.0d;
        Point2D center = new Point2D(centerX, centerY);
        int toHour = value.get();
        double distanceToCenter = distance();
        Point2D currentHourAligned = align(center, toHour);
        Point2D newCoordinates = toCircle(currentHourAligned, center, distanceToCenter);
        circle.setCenterX(newCoordinates.getX());
        circle.setCenterY(newCoordinates.getY());
        line.setEndX(newCoordinates.getX());
        line.setEndY(newCoordinates.getY());
    }

    private static Point2D align(Point2D center, int currentHour) {
        double hourAlignedX = center.getX() + Math.cos(currentHour * TIC_ANGLE - OFFSET);
        double hourAlignedY = center.getY() + Math.sin(currentHour * TIC_ANGLE - OFFSET);
        return new Point2D(hourAlignedX, hourAlignedY);
    }

    private double distance() {
        return length.get() / 2d - POINTER_DISTANCE_OFFSET;
    }

    private static Point2D toCircle(Point2D current, Point2D center, double suggestedLength) {
        Point2D relative = current.subtract(center);
        double distance = relative.magnitude();
        double factor = distance == 0d ? suggestedLength : suggestedLength / distance;
        return relative.multiply(factor).add(center);
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public DoubleProperty lengthValue() {
        return length;
    }

}

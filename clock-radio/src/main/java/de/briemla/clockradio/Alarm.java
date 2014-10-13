package de.briemla.clockradio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Alarm {

	private final IntegerProperty hourProperty;
	private final IntegerProperty minuteProperty;

	public Alarm() {
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}
}

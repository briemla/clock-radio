package de.briemla.clockradio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Alarm {

	private final IntegerProperty hourProperty;
	private final IntegerProperty minuteProperty;
	private final IntegerProperty secondProperty;

	public Alarm() {
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
		secondProperty = new SimpleIntegerProperty();
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}

	public IntegerProperty secondProperty() {
		return secondProperty;
	}
}

package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Alarm {

	private final IntegerProperty hourProperty;
	private final IntegerProperty minuteProperty;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final SimpleBooleanProperty alarmAlreadyStartedProperty;

	public Alarm(SimpleBooleanProperty alarmAlreadyStartedProperty) {
		this.alarmAlreadyStartedProperty = alarmAlreadyStartedProperty;
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
		alarmStartedProperty = new SimpleBooleanProperty();
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}

	public void play(Player player) {
		if (alarmAlreadyStartedProperty.get()) {
			return;
		}
		alarmStartedProperty.set(true);
		try {
			URI uri = new URI("file:///D:/Bibliotheken/Musik/WCG_Theme_Song.mp3");
			player.play(uri);
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
			alarmStartedProperty.set(false);
		}
	}

	public ReadOnlyBooleanProperty alarmStartedProperty() {
		return alarmStartedProperty;
	}
}

package de.briemla.clockradio;

import java.net.URI;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MediaSelector {
	public static final URI DEFAULT_MEDIA = URI.create("file:///D:/Bibliotheken/Musik/WCG_Theme_Song.mp3");

	private final SimpleObjectProperty<Media> mediaProperty;

	public MediaSelector() {
		super();
		mediaProperty = new SimpleObjectProperty<>(player -> player.play(DEFAULT_MEDIA));
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}
}

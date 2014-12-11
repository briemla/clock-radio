package de.briemla.clockradio.controls;

import java.io.File;
import java.net.URI;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.Player;

public class LocalFile implements Media {

	public static final URI DEFAULT_MEDIA = URI.create("file:///D:/Bibliotheken/Musik/WCG_Theme_Song.mp3");
	private final File source;

	public LocalFile() {
		this(new File(DEFAULT_MEDIA));
	}

	public LocalFile(File source) {
		super();
		this.source = source;
	}

	@Override
	public void play(Player player) {
		player.play(source.toURI());
	}

	@Override
	public File getSource() {
		return source;
	}

	@Override
	public String toString() {
		return source.getName();
	}

}

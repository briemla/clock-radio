package de.briemla.clockradio.controls;

import java.io.File;
import java.net.URI;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.Player;

public class LocalFile implements Media {

	private final File source;

	public LocalFile() {
		this(new File(defaultMedia()));
	}

	private static URI defaultMedia() {
		URI defaultMedia = URI
				.create("file:///opt/clock-radio/music/WCG_Theme_Song.mp3");
		if ("amd64".equals(System.getProperty("os.arch").toLowerCase()))
			defaultMedia = URI
					.create("file:///home/lars/Musik/WCG_Theme_Song.mp3");
		return defaultMedia;
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

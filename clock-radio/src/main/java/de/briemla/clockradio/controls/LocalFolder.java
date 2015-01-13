package de.briemla.clockradio.controls;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.Player;

public class LocalFolder implements Media {

	private final Path source;
	private boolean cancelled;

	public LocalFolder() {
		this(defaultFolder());
	}

	private static Path defaultFolder() {
		Path defaultFolder = new File("/opt/clock-radio/music/").toPath();
		if ("amd64".equals(System.getProperty("os.arch").toLowerCase())) {
			defaultFolder = new File("/home/lars/Musik/").toPath(); // WCG_Theme_Song.mp3
		}
		return defaultFolder;
	}

	public LocalFolder(Path source) {
		super();
		this.source = source;
		cancelled = true;
	}

	@Override
	public void play(Player player) {
		cancelled = false;
		try {
			Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path fileOn, BasicFileAttributes attrs) throws IOException {
					if (cancelled) {
						return FileVisitResult.TERMINATE;
					}
					play(fileOn, player);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void play(Path path, Player player) {
		if (isMedia(path)) {
			player.play(path.toUri());
		}
	}

	private static boolean isMedia(Path file) {
		return file.toFile().getName().endsWith(".mp3");
	}

	@Override
	public void stop(Player player) {
		cancelled = true;
		player.stop();
	}

	@Override
	public File getSource() {
		return source.toFile();
	}

	@Override
	public String toString() {
		return getSource().getName();
	}

}

package de.briemla.clockradio.controls;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.briemla.clockradio.PlayableMedia;
import de.briemla.clockradio.player.Player;

public class PlayableLocalFolder implements PlayableMedia {

    private boolean cancelled;
    private final Path source;

    public PlayableLocalFolder(Path source) {
        super();
        this.source = source;
        cancelled = false;
    }

    @Override
    public void stop(Player player) {
        cancelled = true;
        player.stop();
    }

    @Override
    public void play(Player player) {
        List<Path> files = collectFiles();
        Collections.shuffle(files);
        for (Path fileOn : files) {
            if (cancelled) {
                break;
            }
            play(fileOn, player);
        }
    }

    private List<Path> collectFiles() {
        List<Path> files = new ArrayList<>();
        source.forEach(files::add);
        // try {
        // Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
        //
        // @Override
        // public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        // throws IOException {
        // files.add(file);
        // return FileVisitResult.CONTINUE;
        // }
        // });
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        return files;
    }

    private static void play(Path path, Player player) {
        if (isMedia(path)) {
            player.play(path.toUri());
        }
    }

    private static boolean isMedia(Path file) {
        return file.toFile().getName().endsWith(".mp3");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlayableLocalFolder other = (PlayableLocalFolder) obj;
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        return true;
    }

}
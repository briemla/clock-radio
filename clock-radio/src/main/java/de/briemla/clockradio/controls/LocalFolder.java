package de.briemla.clockradio.controls;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.player.Player;

public class LocalFolder implements Media {

    private final Path source;
    private boolean cancelled;

    public LocalFolder() {
        this(defaultFolder());
    }

    /**
     * Select Folder based on os.
     *
     * @return
     */
    private static Path defaultFolder() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return new File("D:\\NAS-Lars\\Musik").toPath();
        }
        if ("amd64".equals(System.getProperty("os.arch").toLowerCase())) {
            return new File("/home/lars/Musik/").toPath();
        }
        return new File("/opt/clock-radio/music/").toPath();
    }

    public LocalFolder(Path source) {
        super();
        this.source = source;
        cancelled = true;
    }

    @Override
    public void play(Player player) {
        cancelled = false;
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
        try {
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    if (cancelled) {
                        return FileVisitResult.TERMINATE;
                    }
                    files.add(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void stop(Player player) {
        cancelled = true;
        player.stop();
    }

    public File getSource() {
        return source.toFile();
    }

    @Override
    public String toString() {
        return getSource().getAbsolutePath();
    }

    public Collection<File> children() {
        List<File> children = Arrays.asList(source.toFile().listFiles(new SupportedFileFilter()));
        children.sort(new FileComparator());
        return children;
    }

    /**
     * Returns the parent directory or itself if there is no parent directory
     *
     * @return
     */
    public LocalFolder parent() {
        if (source.getParent() == null) {
            return this;
        }
        return new LocalFolder(source.getParent());
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
        LocalFolder other = (LocalFolder) obj;
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

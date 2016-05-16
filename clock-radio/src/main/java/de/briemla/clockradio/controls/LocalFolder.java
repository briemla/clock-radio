package de.briemla.clockradio.controls;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.PlayableMedia;

public class LocalFolder implements Media {

    private final Path source;

    public LocalFolder() {
        this(defaultFolder());
    }

    /**
     * Select Folder based on os.
     *
     * @return
     */
    public static Path defaultFolder() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return new File("D:\\NAS-Lars\\Musik").toPath();
        }
        if ("amd64".equals(System.getProperty("os.arch").toLowerCase())) {
            return new File("/media/lars/data/Musik/").toPath();
        }
        return new File("/home/pi/clock-radio/music/").toPath();
    }

    public LocalFolder(Path source) {
        super();
        this.source = source;
    }

    @Override
    public PlayableMedia create() {
        return new PlayableLocalFolder(source);
    }

    public File getSource() {
        return source.toFile();
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

    @Override
    public String toString() {
        return getSource().getAbsolutePath();
    }
}

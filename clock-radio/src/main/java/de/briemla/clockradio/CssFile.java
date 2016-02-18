package de.briemla.clockradio;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javafx.application.Platform;

import de.briemla.clockradio.controls.MainPanel;

public class CssFile {

    private static final class FileWatcher extends Thread {
        private final MainPanel mainPanel;
        private final File file;
        private final WatchService watcher;

        private FileWatcher(String name, WatchService watcher, MainPanel mainPanel, File file) {
            super(name);
            this.watcher = watcher;
            this.mainPanel = mainPanel;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                WatchKey watckKey = watcher.take();

                List<WatchEvent<?>> events = watckKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Platform.runLater(() -> {
                            mainPanel.getStylesheets().clear();
                            mainPanel.getStylesheets().add("file:" + file.getAbsolutePath());
                            System.out.println("CSS file changed");
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
        }
    }

    public static void watch(MainPanel mainPanel) throws MalformedURLException, URISyntaxException {
        if (!"amd64".equals(System.getProperty("os.arch"))) {
            return;
        }
        File file = new File(ClockRadio.class.getResource("clock-radio.css").toURI());
        mainPanel.getStylesheets().add(file.toURI().toURL().toExternalForm());
        Path myDir = file.getParentFile().toPath();

        try {
            WatchService watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

            Thread thread = new FileWatcher("CSS Watcher", watcher, mainPanel, file);
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

}

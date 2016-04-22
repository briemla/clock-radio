package de.briemla.clockradio.controls;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.control.ListCell;

import de.briemla.clockradio.Media;

public class FileCell extends ListCell<File> {

    private static final String EMPTY = "";
    private final ObjectProperty<Media> folderProperty;
    private File currentFile;

    public FileCell(ObjectProperty<Media> folderProperty) {
        this.folderProperty = folderProperty;
        setOnMouseClicked(this::updateFolder);
    }

    private void updateFolder(Event event) {
        if (currentFile != null && currentFile.isDirectory()) {
            folderProperty.set(new LocalFolder(currentFile.toPath()));
            return;
        }
    }

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty && item != null) {
            currentFile = item;
            setText(item.getName());
            return;
        }
        clear();
        // TODO add file/directory icon
    }

    private void clear() {
        currentFile = null;
        setText(EMPTY);
    }
}

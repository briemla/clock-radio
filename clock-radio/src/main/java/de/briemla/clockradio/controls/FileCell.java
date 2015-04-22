package de.briemla.clockradio.controls;

import java.io.File;

import javafx.scene.control.ListCell;

public class FileCell extends ListCell<File> {

	public FileCell() {
	}

	@Override
	protected void updateItem(File item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty && item != null) {
			setText(item.getName());
		}
		// TODO add file/directory icon
	}
}

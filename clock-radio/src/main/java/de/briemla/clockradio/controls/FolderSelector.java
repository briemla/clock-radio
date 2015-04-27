package de.briemla.clockradio.controls;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class FolderSelector extends VBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<LocalFolder> localFolderProperty;

	@FXML
	private Button previous;
	@FXML
	private Label source;
	@FXML
	private ListView<File> directoryContent;
	private final ObservableList<File> currentFiles;

	public FolderSelector() {
		super();
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		localFolderProperty = new SimpleObjectProperty<>(new LocalFolder());
		currentFiles = FXCollections.observableArrayList();
		mediaProperty.addListener((change, oldValue, newValue) -> {
			if (newValue != null && LocalFolder.class.equals(newValue.getClass())) {
				LocalFolder localFolder = (LocalFolder) newValue;
				localFolderProperty.set(localFolder);
				previous.setDisable(localFolder.parent().equals(localFolder));
			}
		});
		source.textProperty().bind(localFolderProperty.asString());
		initializeContentViewer();
	}

	private void initializeContentViewer() {
		directoryContent.setCellFactory(listView -> new FileCell(mediaProperty));
		directoryContent.setItems(currentFiles);
		localFolderProperty.addListener((observable, oldValue, newValue) -> {
			currentFiles.clear();
			currentFiles.addAll(newValue.children());
		});
		if (localFolderProperty.get() != null) {
			currentFiles.addAll(localFolderProperty.get().children());
		}
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}

	@FXML
	public void previous(Event event) {
		LocalFolder localFolder = localFolderProperty.get();
		mediaProperty.set(localFolder.parent());
	}

}

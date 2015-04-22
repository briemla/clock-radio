package de.briemla.clockradio.controls;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class FolderSelector extends VBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<LocalFolder> localFolderProperty;

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
				localFolderProperty.set((LocalFolder) newValue);
			}
		});
		source.textProperty().bind(localFolderProperty.asString());
		initializeContentViewer();
	}

	private void initializeContentViewer() {
		directoryContent.setCellFactory(listView -> new FileCell());
		directoryContent.setItems(currentFiles);
		localFolderProperty.addListener((observable, oldValue, newValue) -> currentFiles.setAll(newValue.children()));
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}

	@FXML
	public void browse(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File currentFolder = currentFolder();
		if (currentFolder.exists()) {
			fileChooser.setInitialDirectory(currentFolder);
		}
		File newSource = fileChooser.showOpenDialog(getScene().getWindow());
		if (newSource == null) {
			return;
		}
		mediaProperty.set(new LocalFolder(newSource.toPath()));
	}

	private File currentFolder() {
		if (mediaProperty.get() instanceof LocalFolder) {
			File currentSource = ((LocalFolder) mediaProperty.get()).getSource();
			return currentSource.isDirectory() ? currentSource : currentSource.getParentFile();
		}
		return new File("");
	}
}

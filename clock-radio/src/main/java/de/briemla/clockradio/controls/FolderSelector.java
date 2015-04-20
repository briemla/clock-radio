package de.briemla.clockradio.controls;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class FolderSelector extends HBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<LocalFolder> localFolderProperty;

	@FXML
	private Label source;

	public FolderSelector() {
		super();
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		localFolderProperty = new SimpleObjectProperty<>(new LocalFolder());
		mediaProperty.addListener((change, oldValue, newValue) -> {
			if (newValue != null && LocalFolder.class.equals(newValue.getClass())) {
				localFolderProperty.set((LocalFolder) newValue);
			}
		});
		source.textProperty().bind(localFolderProperty.asString());
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

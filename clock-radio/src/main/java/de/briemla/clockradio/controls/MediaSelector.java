package de.briemla.clockradio.controls;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class MediaSelector extends VBox {

	@FXML
	private Label source;
	@FXML
	private Button browse;

	private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>(new LocalFile());

	public MediaSelector() {
		super();
		FXUtil.load(this, this);
		source.textProperty().bind(mediaProperty.asString());
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
		mediaProperty.set(new LocalFile(newSource));
	}

	private File currentFolder() {
		File currentSource = mediaProperty.get().getSource();
		return currentSource.isDirectory() ? currentSource : currentSource.getParentFile();
	}
}

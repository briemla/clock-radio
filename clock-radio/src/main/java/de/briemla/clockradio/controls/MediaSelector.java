package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class MediaSelector extends TabPane {

	private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>(new LocalFolder());

	public MediaSelector() {
		super();
		FXUtil.load(this, this);
		registerMediaTypes();
	}

	private void registerMediaTypes() {
		FolderSelector folderSelector = new FolderSelector();
		Tab tab = new Tab();
		tab.setText("Ordner");
		tab.setContent(folderSelector);
		getTabs().add(tab);
		mediaProperty.bindBidirectional(folderSelector.mediaProperty());
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}
}

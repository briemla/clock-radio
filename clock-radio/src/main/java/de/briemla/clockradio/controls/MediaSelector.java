package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class MediaSelector extends TabPane {

	private static final String FOLDER = "Ordner";
	private static final String DAB = "DAB";
	private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>(new LocalFolder());

	public MediaSelector() {
		super();
		FXUtil.load(this, this);
		registerMediaTypes();
	}

	private void registerMediaTypes() {
		FolderSelector folderSelector = new FolderSelector();
		addTab(FOLDER, folderSelector);
		mediaProperty.bindBidirectional(folderSelector.mediaProperty());
		DABSelector dabSelector = new DABSelector();
		addTab(DAB, dabSelector);
		mediaProperty.bindBidirectional(dabSelector.mediaProperty());
	}

	private void addTab(String description, Node node) {
		Tab tab = new Tab();
		tab.setText(description);
		tab.setContent(node);
		getTabs().add(tab);
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}
}

package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.player.Player;

public class MediaSelector extends TabPane {

	private static final String FOLDER = "Ordner";
	private static final String DAB = "DAB";
	private static final String FM = "FM";
	private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>(new LocalFolder());

	public MediaSelector(Settings settings, Player player) {
		super();
		FXUtil.load(this, this);
		registerMediaTypes(settings, player);
	}

	private void registerMediaTypes(Settings settings, Player player) {
		FolderSelector folderSelector = new FolderSelector();
		addTab(FOLDER, folderSelector);
		mediaProperty.bindBidirectional(folderSelector.mediaProperty());
		StationSelector dabSelector = new StationSelector(settings::searchDAB, player);
		addTab(DAB, dabSelector);
		mediaProperty.bindBidirectional(dabSelector.mediaProperty());
		StationSelector fmSelector = new StationSelector(settings::searchFM, player);
		addTab(FM, fmSelector);
		mediaProperty.bindBidirectional(fmSelector.mediaProperty());
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

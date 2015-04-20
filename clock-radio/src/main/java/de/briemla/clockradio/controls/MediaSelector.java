package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;

public class MediaSelector extends HBox {

	private final class MediaChanger implements ChangeListener<Media> {
		@Override
		public void changed(ObservableValue<? extends Media> change, Media oldValue, Media newValue) {
			selector.show(newValue.getClass());
		}
	}

	@FXML
	private VBox mediaType;
	@FXML
	private ViewSwitcher selector;

	private final SimpleObjectProperty<Media> mediaProperty = new SimpleObjectProperty<>(new LocalFolder());

	public MediaSelector() {
		super();
		FXUtil.load(this, this);
		registerMediaTypes();
		mediaProperty.addListener(new MediaChanger());
	}

	private void registerMediaTypes() {
		FolderSelector folderSelector = new FolderSelector();
		selector.addView(LocalFolder.class, folderSelector);
		mediaProperty.bindBidirectional(folderSelector.mediaProperty());
		selector.show(LocalFolder.class);
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}
}

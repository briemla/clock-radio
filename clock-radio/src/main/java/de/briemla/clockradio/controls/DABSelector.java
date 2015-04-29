package de.briemla.clockradio.controls;

import java.io.IOException;

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
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.dabpi.IDABStation;

public class DABSelector extends VBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<RadioMedia> dabStationProperty;

	@FXML
	private Button refresh;
	@FXML
	private Label source;
	@FXML
	private ListView<IDABStation> station;
	private final ObservableList<IDABStation> stationList;
	private final Settings settings;

	public DABSelector(Settings settings) {
		super();
		this.settings = settings;
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		dabStationProperty = new SimpleObjectProperty<>(defaultStation());
		stationList = FXCollections.observableArrayList();
		mediaProperty.addListener((change, oldValue, newValue) -> {
			if (newValue != null && newValue instanceof RadioMedia) {
				RadioMedia dabStation = (RadioMedia) newValue;
				dabStationProperty.set(dabStation);
			}
		});
		source.textProperty().bind(dabStationProperty.asString());
		initializeContentViewer();
	}

	private RadioMedia defaultStation() {
		return new RadioMedia(new NoStation());
	}

	private void initializeContentViewer() {
		station.setCellFactory(listView -> new DABStationCell(mediaProperty));
		station.setItems(stationList);
		updateStations();
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}

	@FXML
	public void refresh(Event event) {
		updateStations();
	}

	// TODO should run in background
	private void updateStations() {
		try {
			stationList.clear();
			stationList.addAll(settings.searchDAB());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}

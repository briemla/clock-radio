package de.briemla.clockradio.controls;

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
import de.briemla.clockradio.dabpi.Station;

public class StationSelector extends VBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<RadioMedia> radioMediaProperty;

	@FXML
	private Button refresh;
	@FXML
	private Label source;
	@FXML
	private ListView<Station> station;
	private final ObservableList<Station> stationList;
	private final SearchStation searcher;

	public StationSelector(SearchStation searcher) {
		super();
		this.searcher = searcher;
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		radioMediaProperty = new SimpleObjectProperty<>(defaultStation());
		stationList = FXCollections.observableArrayList();
		mediaProperty.addListener((change, oldValue, newValue) -> {
			if (newValue != null && newValue instanceof RadioMedia) {
				radioMediaProperty.set((RadioMedia) newValue);
			}
		});
		source.textProperty().bind(radioMediaProperty.asString());
		initializeContentViewer();
	}

	private RadioMedia defaultStation() {
		return new RadioMedia(new NoStation());
	}

	private void initializeContentViewer() {
		station.setCellFactory(listView -> new StationCell(mediaProperty));
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
		stationList.clear();
		stationList.addAll(searcher.search());
	}
}

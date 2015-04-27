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
import de.briemla.clockradio.dabpi.DABStation;

public class DABSelector extends VBox {

	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<DABStation> dabStationProperty;

	@FXML
	private Button refresh;
	@FXML
	private Label source;
	@FXML
	private ListView<DABStation> station;
	private final ObservableList<DABStation> stationList;

	public DABSelector() {
		super();
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		dabStationProperty = new SimpleObjectProperty<>(defaultStation());
		stationList = FXCollections.observableArrayList();
		mediaProperty.addListener((change, oldValue, newValue) -> {
			if (newValue != null && DABStation.class.equals(newValue.getClass())) {
				DABStation dabStation = (DABStation) newValue;
				dabStationProperty.set(dabStation);
			}
		});
		source.textProperty().bind(dabStationProperty.asString());
		initializeContentViewer();
	}

	private DABStation defaultStation() {
		return new DABStation(null, null, null);
	}

	private void initializeContentViewer() {
		// station.setCellFactory(listView -> new DABStationCell(mediaProperty));
		// station.setItems(stationList);
		// dabStationProperty.addListener((observable, oldValue, newValue) -> {
		// stationList.clear();
		// stationList.addAll(newValue.children());
		// });
		// if (dabStationProperty.get() != null) {
		// stationList.addAll(dabStationProperty.get().children());
		// }
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}

	@FXML
	public void refresh(Event event) {
		// TODO search stations
	}
}

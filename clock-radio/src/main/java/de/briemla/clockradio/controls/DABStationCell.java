package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.control.ListCell;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.dabpi.IDABStation;

public class DABStationCell extends ListCell<IDABStation> {

	private static final String EMPTY = "";
	private final ObjectProperty<Media> stationProperty;
	private IDABStation station;

	public DABStationCell(ObjectProperty<Media> stationProperty) {
		this.stationProperty = stationProperty;
		setOnMouseClicked(this::updateStation);
	}

	private void updateStation(Event event) {
		if (station != null) {
			stationProperty.set(new RadioMedia(station));
		}
	}

	@Override
	protected void updateItem(IDABStation item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty && item != null) {
			station = item;
			setText(item.toString());
			return;
		}
		clear();
	}

	private void clear() {
		station = null;
		setText(EMPTY);
	}

}

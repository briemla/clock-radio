package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.scene.control.ListCell;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.dabpi.Station;

public class StationCell extends ListCell<Station> {

    private static final String EMPTY = "";
    private final ObjectProperty<Media> stationProperty;
    private Station station;

    public StationCell(ObjectProperty<Media> stationProperty) {
        this.stationProperty = stationProperty;
        setOnMouseClicked(this::updateStation);
    }

    private void updateStation(Event event) {
        if (station != null) {
            stationProperty.set(new RadioMedia(station));
        }
    }

    @Override
    protected void updateItem(Station item, boolean empty) {
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

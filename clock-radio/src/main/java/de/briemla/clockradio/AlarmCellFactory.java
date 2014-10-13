package de.briemla.clockradio;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AlarmCellFactory implements Callback<ListView<Alarm>, ListCell<Alarm>> {

	@Override
	public ListCell<Alarm> call(ListView<Alarm> param) {
		return new AlarmCell();
	}

}

package de.briemla.clockradio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class AlarmCell extends ListCell<Alarm> {

	private static final String EMPTY = "";
	@FXML
	private AnchorPane container;
	@FXML
	private Label time;
	private Alarm lastItem;

	public AlarmCell() {
		super();
		FXUtil.load(this);
		setGraphic(container);
	}

	@Override
	protected void updateItem(Alarm item, boolean empty) {
		super.updateItem(item, empty);
		if (lastItem != null) {
			time.textProperty().unbind();
		}
		if (item == null) {
			time.setText(EMPTY);
			return;
		}
		// TODO maybe create timeProperty in Alarm class
		time.textProperty().bind(item.hourProperty().asString().concat(":").concat(item.minuteProperty().asString()));
		lastItem = item;
	}

}

package de.briemla.clockradio.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import de.briemla.clockradio.ActiveDays;
import de.briemla.clockradio.FXUtil;

public class ActiveDayEditor extends GridPane {

	@FXML
	private CheckBox monday;
	@FXML
	private CheckBox tuesday;
	@FXML
	private CheckBox wednesday;
	@FXML
	private CheckBox thursday;
	@FXML
	private CheckBox friday;
	@FXML
	private CheckBox saturday;
	@FXML
	private CheckBox sunday;
	@FXML
	private CheckBox workdays;
	@FXML
	private CheckBox weekend;
	@FXML
	private CheckBox daily;

	private final ObjectProperty<ActiveDays> daysProperty;

	public ActiveDayEditor() {
		FXUtil.load(this, this);
		daysProperty = new SimpleObjectProperty<>();
		initWorkdaysBinding();
		initWeekendBinding();
		initDailyBinding();
	}

	private void initWorkdaysBinding() {
		EventHandler<MouseEvent> workdaysChanger = (event) -> workdays.setSelected(isWorkdays());
		monday.addEventHandler(MouseEvent.MOUSE_CLICKED, workdaysChanger);
		tuesday.addEventHandler(MouseEvent.MOUSE_CLICKED, workdaysChanger);
		wednesday.addEventHandler(MouseEvent.MOUSE_CLICKED, workdaysChanger);
		thursday.addEventHandler(MouseEvent.MOUSE_CLICKED, workdaysChanger);
		friday.addEventHandler(MouseEvent.MOUSE_CLICKED, workdaysChanger);
		daily.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			monday.setSelected(workdays.isSelected());
			tuesday.setSelected(workdays.isSelected());
			wednesday.setSelected(workdays.isSelected());
			thursday.setSelected(workdays.isSelected());
			friday.setSelected(workdays.isSelected());
		});
	}

	private void initWeekendBinding() {
		EventHandler<MouseEvent> weekendChanger = (event) -> weekend.setSelected(isWeekend());
		saturday.addEventHandler(MouseEvent.MOUSE_CLICKED, weekendChanger);
		sunday.addEventHandler(MouseEvent.MOUSE_CLICKED, weekendChanger);
		daily.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			saturday.setSelected(weekend.isSelected());
			sunday.setSelected(weekend.isSelected());
		});
	}

	private void initDailyBinding() {
		EventHandler<MouseEvent> dailyChanger = (event) -> daily.setSelected(isDaily());
		monday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		tuesday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		wednesday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		thursday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		friday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		saturday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		sunday.addEventHandler(MouseEvent.MOUSE_CLICKED, dailyChanger);
		daily.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			monday.setSelected(daily.isSelected());
			tuesday.setSelected(daily.isSelected());
			wednesday.setSelected(daily.isSelected());
			thursday.setSelected(daily.isSelected());
			friday.setSelected(daily.isSelected());
			saturday.setSelected(daily.isSelected());
			sunday.setSelected(daily.isSelected());
		});
	}

	private Boolean isWorkdays() {
		return monday.isSelected() && tuesday.isSelected() && wednesday.isSelected() && thursday.isSelected()
				&& friday.isSelected();
	}

	private boolean isWeekend() {
		return saturday.isSelected() && sunday.isSelected();
	}

	private boolean isDaily() {
		return monday.isSelected() && tuesday.isSelected() && wednesday.isSelected() && thursday.isSelected()
				&& friday.isSelected() && saturday.isSelected() && sunday.isSelected();
	}

	public ObjectProperty<ActiveDays> daysProperty() {
		return daysProperty;
	}

}

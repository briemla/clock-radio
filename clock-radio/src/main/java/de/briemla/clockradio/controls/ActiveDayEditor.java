package de.briemla.clockradio.controls;

import java.time.DayOfWeek;
import java.util.EnumSet;

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

	private static final ActiveDays DAILY = new ActiveDays(EnumSet.allOf(DayOfWeek.class));

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
		daysProperty = new SimpleObjectProperty<>(DAILY);
		initWorkdaysBinding();
		initWeekendBinding();
		initDailyBinding();
		initUpdateActiveDays();
		initDaysListener();
		updateValues();
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

	private void initUpdateActiveDays() {
		monday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.MONDAY));
		tuesday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.TUESDAY));
		wednesday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.WEDNESDAY));
		thursday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.THURSDAY));
		friday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.FRIDAY));
		saturday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.SATURDAY));
		sunday.selectedProperty().addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.SUNDAY));
	}

	private void initDaysListener() {
		daysProperty.addListener((change, oldValue, newValue) -> updateValues());
	}

	private void updateValues() {
		monday.setSelected(daysProperty.get().contains(DayOfWeek.MONDAY));
		tuesday.setSelected(daysProperty.get().contains(DayOfWeek.TUESDAY));
		wednesday.setSelected(daysProperty.get().contains(DayOfWeek.WEDNESDAY));
		thursday.setSelected(daysProperty.get().contains(DayOfWeek.THURSDAY));
		friday.setSelected(daysProperty.get().contains(DayOfWeek.FRIDAY));
		saturday.setSelected(daysProperty.get().contains(DayOfWeek.SATURDAY));
		sunday.setSelected(daysProperty.get().contains(DayOfWeek.SUNDAY));
		workdays.setSelected(isWorkdays());
		weekend.setSelected(isWeekend());
		daily.setSelected(isDaily());
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

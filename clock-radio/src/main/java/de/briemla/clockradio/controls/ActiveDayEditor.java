package de.briemla.clockradio.controls;

import java.time.DayOfWeek;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import de.briemla.clockradio.ActiveDays;
import de.briemla.clockradio.FxUtil;

public class ActiveDayEditor extends GridPane {

    private final class SelectWeekend implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            saturday.setSelected(true);
            sunday.setSelected(true);
        }
    }

    private final class SelectWorkDays implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            monday.setSelected(true);
            tuesday.setSelected(true);
            wednesday.setSelected(true);
            thursday.setSelected(true);
            friday.setSelected(true);
        }
    }

    private final class SelectAll implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            monday.setSelected(true);
            tuesday.setSelected(true);
            wednesday.setSelected(true);
            thursday.setSelected(true);
            friday.setSelected(true);
            saturday.setSelected(true);
            sunday.setSelected(true);
        }
    }

    @FXML
    private ToggleButton monday;
    @FXML
    private ToggleButton tuesday;
    @FXML
    private ToggleButton wednesday;
    @FXML
    private ToggleButton thursday;
    @FXML
    private ToggleButton friday;
    @FXML
    private ToggleButton saturday;
    @FXML
    private ToggleButton sunday;
    @FXML
    private Button workdays;
    @FXML
    private Button weekend;
    @FXML
    private Button daily;

    private final ObjectProperty<ActiveDays> daysProperty;

    public ActiveDayEditor() {
        FxUtil.load(this, this);
        daysProperty = new SimpleObjectProperty<>(new ActiveDays());
        initWorkdaysBinding();
        initWeekendBinding();
        initDailyBinding();
        initUpdateActiveDays();
        initDaysListener();
        updateValues();
    }

    private void initWorkdaysBinding() {
        workdays.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectWorkDays());
    }

    private void initWeekendBinding() {
        weekend.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectWeekend());
    }

    private void initDailyBinding() {
        daily.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectAll());
    }

    private void initUpdateActiveDays() {
        monday.selectedProperty()
              .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.MONDAY));
        tuesday.selectedProperty()
               .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.TUESDAY));
        wednesday.selectedProperty()
                 .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.WEDNESDAY));
        thursday.selectedProperty()
                .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.THURSDAY));
        friday.selectedProperty()
              .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.FRIDAY));
        saturday.selectedProperty()
                .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.SATURDAY));
        sunday.selectedProperty()
              .addListener(new ActiveDaysChanger(daysProperty, DayOfWeek.SUNDAY));
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
    }

    public ObjectProperty<ActiveDays> daysProperty() {
        return daysProperty;
    }

}

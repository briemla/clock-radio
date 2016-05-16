package de.briemla.clockradio.controls;

import java.util.HashMap;
import java.util.List;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FxUtil;
import de.briemla.clockradio.Settings;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class AlarmView extends HBox {

    @FXML
    private HBox container;

    private final ObservableList<Alarm> alarms;
    private Settings settings;
    private final HashMap<Alarm, AlarmCell> alarmToCell;

    public AlarmView() {
        super();
        FxUtil.load(this, this);
        alarms = FXCollections.observableArrayList();
        alarmToCell = new HashMap<>();
        alarms.addListener(new ListChangeListener<Alarm>() {

            @Override
            public void onChanged(Change<? extends Alarm> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        List<? extends Alarm> addedSubList = change.getAddedSubList();
                        for (Alarm alarm : addedSubList) {
                            AlarmCell alarmCell = new AlarmCell(alarm, settings);
                            container.getChildren().add(container.getChildren().size() - 1, alarmCell);
                            alarmToCell.put(alarm, alarmCell);
                        }
                    }
                    if (change.wasRemoved()) {
                        List<? extends Alarm> removedSubList = change.getRemoved();
                        for (Alarm alarm : removedSubList) {
                            container.getChildren().remove(alarmToCell.get(alarm));
                        }
                    }
                }
            }
        });
    }

    public void setSettings(Settings settings) {
        if (this.settings != null) {
            Bindings.unbindContentBidirectional(alarms, this.settings.getAlarms());
        }
        this.settings = settings;
        Bindings.bindContentBidirectional(alarms, this.settings.getAlarms());
    }

    @FXML
    public void add(ActionEvent event) {
        if (settings == null) {
            throw new NullPointerException("Settings have not been initialized in AlarmView");
        }

        settings.addAlarm();
    }

}

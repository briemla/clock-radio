package de.briemla.clockradio.controls;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.player.Player;

public class MainPanel extends StackPane {

    private final class ActivationTask extends TimerTask {

        private final Property<Boolean> property;

        public ActivationTask(Property<Boolean> property) {
            this.property = property;
        }

        @Override
        public void run() {
            Platform.runLater(() -> property.setValue(false));
        }
    }

    private static final long INACTIVE_TIMEOUT = 10 * 1000;
    @FXML
    private DefaultableViewSwitcher viewSwitcher;
    @FXML
    private AlarmView alarm;

    private final ActivePseudoClassProperty active;
    private final Settings settings;
    private Timer timer;

    public MainPanel(Player player, Trigger trigger) {
        super();
        FXUtil.load(this, this);
        active = new ActivePseudoClassProperty(this);
        settings = new Settings(viewSwitcher, player);
        alarm.setSettings(settings);
        trigger.bind(settings.getAlarms());
        Node clock = new DigitalClock();
        viewSwitcher.setDefaultView(clock);
        AlarmMenu alarmMenu = new AlarmMenu(viewSwitcher, settings, player);
        viewSwitcher.addView(Alarm.class, alarmMenu);
        addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (!active.get()) {
                event.consume();
            }
            active.set(true);
            resetInactiveTimer();
        });
        active.set(true);
        resetInactiveTimer();
        settings.addAlarm();
        viewSwitcher.showDefault();
    }

    private void resetInactiveTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer("ActiveTimer", true);
        timer.schedule(new ActivationTask(active), INACTIVE_TIMEOUT);
    }

    @FXML
    public void shutdown(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void stopSound(ActionEvent event) {
        settings.stopCurrentAlarm();
    }

}

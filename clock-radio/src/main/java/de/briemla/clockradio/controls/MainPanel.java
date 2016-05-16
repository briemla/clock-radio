package de.briemla.clockradio.controls;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.AlarmFactory;
import de.briemla.clockradio.AlarmStorage;
import de.briemla.clockradio.FxUtil;
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.TimeProvider;
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

    private static final long inactiveTimeout = Duration.of(10, SECONDS).toMillis();;
    @FXML
    private DefaultableViewSwitcher viewSwitcher;
    @FXML
    private AlarmView alarm;

    private final ActivePseudoClassProperty active;
    private final Settings settings;
    private Timer timer;
    private final Trigger trigger;

    public MainPanel(Player player, Trigger trigger, AlarmFactory alarmFactory,
            TimeProvider timeProvider, AlarmStorage storage) {
        super();
        this.trigger = trigger;
        FxUtil.load(this, this);
        active = new ActivePseudoClassProperty(this);
        settings = new Settings(viewSwitcher, player, alarmFactory, storage);
        alarm.setSettings(settings);
        trigger.bind(settings.getAlarms());
        Node clock = new DigitalClock(timeProvider);
        viewSwitcher.setDefaultView(clock);
        AlarmMenu alarmMenu = new AlarmMenu(viewSwitcher, settings, player);
        viewSwitcher.addView(Alarm.class, alarmMenu);
        addEventFilter(MouseEvent.MOUSE_PRESSED, showControls());
        active.set(true);
        resetInactiveTimer();
        settings.initialzeAlarms();
        viewSwitcher.showDefault();
        timeProvider.timeProperty().addListener(event -> trigger.startNow());
    }

    private EventHandler<? super MouseEvent> showControls() {
        return event -> {
            if (!active.get()) {
                event.consume();
            }
            active.set(true);
            resetInactiveTimer();
        };
    }

    private void resetInactiveTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer("ActiveTimer", true);
        timer.schedule(new ActivationTask(active), inactiveTimeout);
    }

    @FXML
    public void shutdown(ActionEvent event) {
        stopSound(event);
        Platform.exit();
    }

    @FXML
    public void stopSound(ActionEvent event) {
        // TODO cleanup and call only one of both
        settings.stopCurrentAlarm();
        trigger.stop();
    }

}

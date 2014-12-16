package de.briemla.clockradio;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainPanel extends VBox {

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
	private ViewSwitcher viewSwitch;
	@FXML
	private AlarmView alarm;

	private final ActivePseudoClassProperty active;
	private final Player player;
	private final Settings settings;
	private Timer timer;

	public MainPanel(Player player) {
		super();
		this.player = player;
		FXUtil.load(this, this);
		active = new ActivePseudoClassProperty(this);
		settings = new Settings(viewSwitch, player);
		alarm.setSettings(settings);
		Node clock = new Clock();
		viewSwitch.setDefaultView(clock);
		AlarmSettings alarmSettings = new AlarmSettings();
		viewSwitch.addView(Alarm.class, alarmSettings);
		addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
			active.set(true);
			resetInactiveTimer();
		});
		active.set(true);
		resetInactiveTimer();
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
	public void openSettings(ActionEvent event) {

	}

	public void stopSound(ActionEvent event) {
		player.stop();
	}

}

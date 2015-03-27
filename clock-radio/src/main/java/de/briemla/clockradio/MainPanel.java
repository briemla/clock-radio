package de.briemla.clockradio;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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
	private ViewSwitcher viewSwitch;
	@FXML
	private AnchorPane overlay;
	@FXML
	private AlarmView alarm;
	@FXML
	private AlarmMenu alarmMenu;

	private final ActivePseudoClassProperty active;
	private final Settings settings;
	private Timer timer;
	private Process alsaloop;

	public MainPanel(Player player) {
		super();
		FXUtil.load(this, this);
		active = new ActivePseudoClassProperty(this);
		overlay.setPickOnBounds(true);
		// overlay.setMouseTransparent(true);
		OverlaySwitcher overlaySwitcher = new OverlaySwitcher(overlay);
		overlaySwitcher.addView(Alarm.class, alarmMenu);
		settings = new Settings(viewSwitch, player, overlaySwitcher);
		alarm.setSettings(settings);
		Node clock = new Clock();
		viewSwitch.setDefaultView(clock);
		AlarmSettings alarmSettings = new AlarmSettings(settings);
		viewSwitch.addView(Alarm.class, alarmSettings);
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
		viewSwitch.showDefault();
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

	@FXML
	public void shutdown(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void stopSound(ActionEvent event) {
		settings.stopCurrentAlarm();
		// player.stop();
	}

	@FXML
	public void startRadio(ActionEvent event) {
		// TODO implement this
		// RadioExecutor executor;
		// CommandFactory factory;
		// RadioPlayer radioPlayer = new RadioPlayer(new DabpiController(executor, factory));
		// radioPlayer.scan();
		// Runtime runtime = Runtime.getRuntime();
		// try {
		// alsaloop = runtime.exec("alsaloop -C hw:1,0");
		// Process process = runtime.exec("/home/pi/dabpi_ctl/startRadio.sh");
		// process.waitFor();
		// } catch (IOException | InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}

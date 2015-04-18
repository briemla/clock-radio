package de.briemla.clockradio.controls;

import java.util.Timer;
import java.util.TimerTask;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.player.Player;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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
	private ViewSwitcher viewSwitcher;
	@FXML
	private AlarmView alarm;

	private final ActivePseudoClassProperty active;
	private final Settings settings;
	private Timer timer;

	public MainPanel(Player player) {
		super();
		FXUtil.load(this, this);
		active = new ActivePseudoClassProperty(this);
		settings = new Settings(viewSwitcher, player);
		alarm.setSettings(settings);
		Node clock = new DigitalClock();
		viewSwitcher.setDefaultView(clock);
		AlarmMenu alarmMenu = new AlarmMenu(viewSwitcher, settings);
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
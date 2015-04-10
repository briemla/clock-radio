package de.briemla.clockradio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import de.briemla.clockradio.controls.LocalFolder;

public class Alarm {

	public static class AlarmTimer implements ChangeListener<Number> {

		private Timer startTimer;
		private final Alarm alarm;
		private Timer stopTimer;

		public AlarmTimer(Alarm alarm) {
			super();
			this.alarm = alarm;
		}

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			stopTimer();
			startTimer();
		}

		private void stopTimer() {
			if (startTimer != null) {
				startTimer.cancel();
				startTimer = null;
			}
			if (stopTimer != null) {
				stopTimer.cancel();
				stopTimer = null;
			}
		}

		private void startTimer() {
			startTimer = new Timer("AlarmTimer", true);
			// TODO: Maybe use Timer#schedule to run the timer on specified
			// dates:
			// Timer timer new Timer();
			// Thread myThread= // Your thread
			// Calendar date = Calendar.getInstance();
			// date.set(
			// Calendar.DAY_OF_WEEK,
			// Calendar.SUNDAY
			// );
			// date.set(Calendar.HOUR, 0);
			// date.set(Calendar.MINUTE, 0);
			// date.set(Calendar.SECOND, 0);
			// date.set(Calendar.MILLISECOND, 0);
			// // Schedule to run every Sunday in midnight
			// timer.schedule(
			// new SampleTask (myThread),
			// date.getTime(),
			// 1000 * 60 * 60 * 24 * 7
			// );
			startTimer.scheduleAtFixedRate(new AlarmTask(alarm), alarm.alarmDate(), Long.MAX_VALUE);
			stopTimer = new Timer("AlarmStopTimer", true);
			stopTimer.scheduleAtFixedRate(new AlarmStopTask(alarm), alarm.alarmStopDate(), Long.MAX_VALUE);
		}
	}

	public static class AlarmTask extends TimerTask {

		private final Alarm alarm;

		public AlarmTask(Alarm alarm) {
			this.alarm = alarm;
		}

		@Override
		public void run() {
			alarm.play();
		}
	}

	public static class AlarmStopTask extends TimerTask {

		private final Alarm alarm;

		public AlarmStopTask(Alarm alarm) {
			this.alarm = alarm;
		}

		@Override
		public void run() {
			alarm.stop();
		}
	}

	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;
	private final SimpleObjectProperty<Duration> durationProperty;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final SimpleBooleanProperty alarmAlreadyStartedProperty;
	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleBooleanProperty activated = new SimpleBooleanProperty(true);
	private final Player mediaPlayer;

	public Alarm(SimpleBooleanProperty alarmAlreadyStartedProperty, Player mediaPlayer) {
		this.alarmAlreadyStartedProperty = alarmAlreadyStartedProperty;
		this.mediaPlayer = mediaPlayer;
		LocalTime now = LocalTime.now().plusMinutes(1);
		hourProperty = new SimpleIntegerProperty(now.getHour());
		minuteProperty = new SimpleIntegerProperty(now.getMinute());
		durationProperty = new SimpleObjectProperty<>(Duration.ofHours(1));
		alarmStartedProperty = new SimpleBooleanProperty();
		mediaProperty = new SimpleObjectProperty<>(new LocalFolder());
		AlarmTimer alarmTimer = new AlarmTimer(this);
		alarmTimer.startTimer();
		hourProperty.addListener(alarmTimer);
		minuteProperty.addListener(alarmTimer);
		activated.addListener((change, oldValue, newValue) -> {
			if (newValue) {
				alarmTimer.startTimer();
				return;
			}
			alarmTimer.stopTimer();
		});
	}

	public void stop() {
		if (alarmStartedProperty.get()) {
			mediaProperty().get().stop(mediaPlayer);
			alarmStartedProperty.set(false);
		}
	}

	public Duration getDuration() {
		return durationProperty.get();
	}

	public IntegerProperty hourProperty() {
		return hourProperty;
	}

	public IntegerProperty minuteProperty() {
		return minuteProperty;
	}

	public ObjectProperty<Media> mediaProperty() {
		return mediaProperty;
	}

	public void play() {
		if (alarmAlreadyStartedProperty.get()) {
			return;
		}
		alarmStartedProperty.set(true);
		try {
			mediaProperty.get().play(mediaPlayer);
		} catch (Exception exception) {
			exception.printStackTrace();
			alarmStartedProperty.set(false);
		}
	}

	public ReadOnlyBooleanProperty alarmStartedProperty() {
		return alarmStartedProperty;
	}

	private Date alarmDate() {
		return convertToDate(alarmLocalDate());
	}

	private static Date convertToDate(LocalDateTime date) {
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}

	private LocalDateTime alarmLocalDate() {
		LocalDateTime date = LocalDateTime.now();
		if (date.getHour() > hourProperty.get() || date.getHour() == hourProperty.get()
		        && date.getMinute() >= minuteProperty.get()) {
			date = date.plusDays(1);
		}
		date = date.withHour(hourProperty.get());
		date = date.withMinute(minuteProperty.get());
		return date.withSecond(0).withNano(0);
	}

	private Date alarmStopDate() {
		return convertToDate(alarmLocalDate().plus(getDuration()));
	}

	public Property<Boolean> activatedProperty() {
		return activated;
	}

	public void kill() {
		stop();
		activated.set(false);
	}
}

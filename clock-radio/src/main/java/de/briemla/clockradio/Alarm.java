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
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import de.briemla.clockradio.controls.LocalFile;

public class Alarm {

	public static class AlarmTimer implements ChangeListener<Number> {

		private Timer timer;
		private final Alarm alarm;

		public AlarmTimer(Alarm alarm) {
			super();
			this.alarm = alarm;
		}

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
			startTimer();
		}

		private void startTimer() {
			timer = new Timer("AlarmTimer", true);
			timer.scheduleAtFixedRate(new AlarmTask(alarm), alarm.alarmDate(), Long.MAX_VALUE);
		}
	}

	public static class AlarmTask extends TimerTask {

		private final Alarm alarm;

		public AlarmTask(Alarm alarm) {
			this.alarm = alarm;
		}

		@Override
		public void run() {
			Duration duration = alarm.getDuration();
			alarm.play();
			try {
				Thread.sleep(duration.toMillis());
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
			alarm.stop();
		}
	}

	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;
	private final SimpleObjectProperty<Duration> durationProperty;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final SimpleBooleanProperty alarmAlreadyStartedProperty;
	private final SimpleObjectProperty<Media> mediaProperty;
	private final Player mediaPlayer;

	public Alarm(SimpleBooleanProperty alarmAlreadyStartedProperty, Player mediaPlayer) {
		this.alarmAlreadyStartedProperty = alarmAlreadyStartedProperty;
		this.mediaPlayer = mediaPlayer;
		hourProperty = new SimpleIntegerProperty(LocalTime.now().getHour());
		minuteProperty = new SimpleIntegerProperty(LocalTime.now().getMinute() + 1);
		durationProperty = new SimpleObjectProperty<>(Duration.ofSeconds(10));
		alarmStartedProperty = new SimpleBooleanProperty();
		mediaProperty = new SimpleObjectProperty<>(new LocalFile());
		AlarmTimer alarmTimer = new AlarmTimer(this);
		alarmTimer.startTimer();
		hourProperty.addListener(alarmTimer);
		minuteProperty.addListener(alarmTimer);
	}

	public void stop() {
		mediaPlayer.stop();
		alarmStartedProperty.set(false);
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
		LocalDateTime date = LocalDateTime.now();
		if (date.getHour() > hourProperty.get() || date.getHour() == hourProperty.get() && date.getMinute() >= minuteProperty.get()) {
			date = date.plusDays(1);
		}
		date = date.withHour(hourProperty.get());
		date = date.withMinute(minuteProperty.get());
		date = date.withSecond(0);
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}
}

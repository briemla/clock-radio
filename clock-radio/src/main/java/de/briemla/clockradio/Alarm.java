package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
	private final Player player;

	public Alarm(SimpleBooleanProperty alarmAlreadyStartedProperty, Player player) {
		this.alarmAlreadyStartedProperty = alarmAlreadyStartedProperty;
		this.player = player;
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
		durationProperty = new SimpleObjectProperty<>(Duration.ofSeconds(10));
		alarmStartedProperty = new SimpleBooleanProperty();
		AlarmTimer alarmTimer = new AlarmTimer(this);
		hourProperty.addListener(alarmTimer);
		minuteProperty.addListener(alarmTimer);
	}

	public void stop() {
		player.stop();
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

	public void play() {
		if (alarmAlreadyStartedProperty.get()) {
			return;
		}
		alarmStartedProperty.set(true);
		try {
			URI uri = new URI("file:///D:/Bibliotheken/Musik/WCG_Theme_Song.mp3");
			player.play(uri);
		} catch (URISyntaxException exception) {
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

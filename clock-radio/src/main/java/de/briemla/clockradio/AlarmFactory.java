package de.briemla.clockradio;

public interface AlarmFactory {

    Alarm create();

    Alarm fromStorage(String storedAlarm);

}

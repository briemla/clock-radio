package de.briemla.clockradio;

import de.briemla.clockradio.player.PlayerFactory;

// TODO find a better name
public class RealAlarmFactory implements AlarmFactory {

    private final TimeProvider timeProvider;
    private final PlayerFactory playerFactory;
    private SaveTrigger saveTrigger;

    public RealAlarmFactory(PlayerFactory playerFactory, TimeProvider timeProvider) {
        super();
        this.playerFactory = playerFactory;
        this.timeProvider = timeProvider;
        saveTrigger = () -> {};
    }

    public void initialize(SaveTrigger saveTrigger) {
        this.saveTrigger = saveTrigger;
    }

    @Override
    public Alarm create() {
        return new Alarm(playerFactory, timeProvider, saveTrigger);
    }

    @Override
    public Alarm fromStorage(String storedAlarm) {
        return Alarm.fromStorage(storedAlarm, playerFactory, timeProvider, saveTrigger);
    }

}

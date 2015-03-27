package de.briemla.clockradio;

public interface CommandFactory {

	Command<SwitchToDABResult> switchToDAB();

	Command<SwitchToFMResult> switchToFM();

}

package de.briemla.clockradio.dabpi;


public interface RadioExecutor {

	<T extends RadioResult> T execute(Command<T> command);
}

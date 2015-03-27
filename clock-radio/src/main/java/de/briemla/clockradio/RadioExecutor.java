package de.briemla.clockradio;

public interface RadioExecutor {

	<T extends RadioResult> T execute(Command<T> command);
}

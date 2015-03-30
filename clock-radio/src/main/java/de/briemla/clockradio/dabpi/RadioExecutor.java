package de.briemla.clockradio.dabpi;

import java.io.IOException;

public interface RadioExecutor {

	<T extends RadioResult> T execute(Command<T> command) throws IOException;
}

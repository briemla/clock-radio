package de.briemla.clockradio.dabpi;

import java.io.IOException;

public interface RadioExecutor {

    <T> T execute(Command<T> command) throws IOException;
}

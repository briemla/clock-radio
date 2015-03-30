package de.briemla.clockradio.dabpi;

import java.io.IOException;

import de.briemla.clockradio.Output;

public interface Command<T extends RadioResult> {

	public String serialize();

	public T parse(Output output) throws IOException;

}

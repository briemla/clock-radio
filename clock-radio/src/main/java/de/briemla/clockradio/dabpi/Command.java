package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;

public interface Command<T extends RadioResult> {

	public String getParameter();

	public T parse(Output output);

}

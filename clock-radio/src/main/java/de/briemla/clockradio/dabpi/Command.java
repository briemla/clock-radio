package de.briemla.clockradio.dabpi;

import java.io.IOException;

public interface Command<T> {

    public String serialize();

    public T parse(Output output) throws IOException;

}

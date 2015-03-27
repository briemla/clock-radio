package de.briemla.clockradio;

public interface Command<T extends RadioResult> {

	public String getParameter();

	public T parse(Output output);

}

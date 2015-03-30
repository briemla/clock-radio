package de.briemla.clockradio.dabpi.command;

import java.io.IOException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.Command;

public abstract class BaseCommand<T> implements Command<T> {

	private final String parameter;

	public BaseCommand(String parameter) {
		super();
		this.parameter = parameter;
	}

	@Override
	public String serialize() {
		return " -" + parameter;
	}

	@Override
	public T parse(Output output) throws IOException {
		if (output.isErrorEmpty()) {
			return parseSpecialized(output);
		}
		throw new IOException(output.concatErrorMessage());
	}

	protected abstract T parseSpecialized(Output output);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		BaseCommand<T> other = (BaseCommand<T>) obj;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseCommand [parameter=" + parameter + "]";
	}

}
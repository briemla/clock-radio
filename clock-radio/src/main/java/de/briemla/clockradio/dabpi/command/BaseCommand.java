package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Command;
import de.briemla.clockradio.dabpi.RadioResult;

public abstract class BaseCommand<T extends RadioResult> implements Command<T> {

	private final String parameter;

	public BaseCommand(String parameter) {
		super();
		this.parameter = parameter;
	}

	@Override
	public String serialize() {
		return parameter;
	}

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
		BaseCommand<RadioResult> other = (BaseCommand<RadioResult>) obj;
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
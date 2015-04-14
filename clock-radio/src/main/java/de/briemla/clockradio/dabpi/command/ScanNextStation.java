package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.ScanDirection;

public class ScanNextStation extends BaseCommand<Void> {

	private final ScanDirection direction;

	public ScanNextStation(ScanDirection direction) {
		super("l");
		this.direction = direction;
	}

	@Override
	public String serialize() {
		return super.serialize() + " " + direction.serialize();
	}

	@Override
	protected Void parseSpecialized(Output output) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScanNextStation other = (ScanNextStation) obj;
		if (direction != other.direction)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScanNextStation [direction=" + direction + "]";
	}

}

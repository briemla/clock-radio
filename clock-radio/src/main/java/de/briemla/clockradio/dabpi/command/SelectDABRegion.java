package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;

public class SelectDABRegion extends BaseCommand<Void> {

	private final Region region;

	public SelectDABRegion(Region region) {
		super("j");
		this.region = region;
	}

	@Override
	public String serialize() {
		return super.serialize() + " " + region.serialize();
	}

	@Override
	protected Void parseSpecialized(Output output) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		SelectDABRegion other = (SelectDABRegion) obj;
		if (region != other.region)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABRegion [region=" + region + "]";
	}

}

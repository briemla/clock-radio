package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABRegion;

public class SelectDABRegion extends BaseCommand<DABRegion> {

	private final Integer regionId;

	public SelectDABRegion(Integer regionId) {
		super("j");
		this.regionId = regionId;
	}

	@Override
	protected DABRegion parseSpecialized(Output output) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
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
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABRegion [regionId=" + regionId + "]";
	}

}

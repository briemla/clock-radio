package de.briemla.clockradio.dabpi.result;

import de.briemla.clockradio.dabpi.RadioResult;

public class DABRegion implements RadioResult {

	private final boolean successful;
	private final Integer regionId;

	public DABRegion(boolean successful, Integer regionId) {
		this.successful = successful;
		this.regionId = regionId;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
		result = prime * result + (successful ? 1231 : 1237);
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
		DABRegion other = (DABRegion) obj;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABRegionResult [successful=" + successful + ", regionId=" + regionId + "]";
	}

}
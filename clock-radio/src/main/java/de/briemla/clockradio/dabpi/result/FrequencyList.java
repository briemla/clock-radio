package de.briemla.clockradio.dabpi.result;

import de.briemla.clockradio.dabpi.RadioResult;

public class FrequencyList implements RadioResult {

	private final Integer regionId;

	public FrequencyList(Integer regionId) {
		this.regionId = regionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
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
		FrequencyList other = (FrequencyList) obj;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FrequencyList [regionId=" + regionId + "]";
	}

}

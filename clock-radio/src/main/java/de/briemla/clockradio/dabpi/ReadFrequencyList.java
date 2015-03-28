package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.FrequencyList;

public class ReadFrequencyList extends BaseCommand<FrequencyList> {

	private final Integer regionId;

	public ReadFrequencyList(Integer regionId) {
		super("k");
		this.regionId = regionId;
	}

	@Override
	public FrequencyList parse(Output output) {
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
		ReadFrequencyList other = (ReadFrequencyList) obj;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReadFrequencyList [regionId=" + regionId + "]";
	}

}

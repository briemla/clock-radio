package de.briemla.clockradio.dabpi.result;

import de.briemla.clockradio.dabpi.RadioResult;

public class DABService implements RadioResult {

	private final Integer serviceId;

	public DABService(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
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
		DABService other = (DABService) obj;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DABService [serviceId=" + serviceId + "]";
	}

}

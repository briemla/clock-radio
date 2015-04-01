package de.briemla.clockradio.dabpi.result;

public class DABService {

	private final Integer serviceNumber;
	private final String serviceName;

	public DABService(Integer serviceNumber, String serviceName) {
		this.serviceNumber = serviceNumber;
		this.serviceName = serviceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceNumber == null) ? 0 : serviceNumber.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
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
		if (serviceNumber == null) {
			if (other.serviceNumber != null)
				return false;
		} else if (!serviceNumber.equals(other.serviceNumber))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DABService [serviceNumber=" + serviceNumber + ", serviceName=" + serviceName + "]";
	}

}

package de.briemla.clockradio;

public class StartDABServiceResult implements RadioResult {

	private final boolean successful;
	private final Integer serviceId;

	public StartDABServiceResult(boolean successful, Integer serviceId) {
		this.successful = successful;
		this.serviceId = serviceId;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
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
		StartDABServiceResult other = (StartDABServiceResult) obj;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StartDABServiceResult [successful=" + successful + ", serviceId=" + serviceId + "]";
	}

}

package de.briemla.clockradio.dabpi.result;

public class FMStatus {

	private final Integer successful;

	public FMStatus(Integer frequency) {
		successful = frequency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((successful == null) ? 0 : successful.hashCode());
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
		FMStatus other = (FMStatus) obj;
		if (successful == null) {
			if (other.successful != null)
				return false;
		} else if (!successful.equals(other.successful))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FMStatus [successful=" + successful + "]";
	}

}

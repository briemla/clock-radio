package de.briemla.clockradio;

public class ScanNextStationResult implements RadioResult {

	private final boolean successful;
	private final ScanDirection direction;

	public ScanNextStationResult(boolean successful, ScanDirection direction) {
		this.successful = successful;
		this.direction = direction;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
		ScanNextStationResult other = (ScanNextStationResult) obj;
		if (direction != other.direction)
			return false;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScanNextStationResult [successful=" + successful + ", direction=" + direction + "]";
	}

}

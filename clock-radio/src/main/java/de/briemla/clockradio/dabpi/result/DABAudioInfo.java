package de.briemla.clockradio.dabpi.result;

import de.briemla.clockradio.dabpi.RadioResult;

public class DABAudioInfo implements RadioResult {

	private final boolean successful;

	public DABAudioInfo(boolean successful) {
		this.successful = successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DABAudioInfo other = (DABAudioInfo) obj;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReadDABAudioInfoResult [successful=" + successful + "]";
	}

}

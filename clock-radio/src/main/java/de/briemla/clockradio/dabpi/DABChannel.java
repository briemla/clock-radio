package de.briemla.clockradio.dabpi;

public class DABChannel implements RadioResult {

	private final boolean successful;
	private final Integer channelId;

	public DABChannel(boolean successful, Integer channelId) {
		this.successful = successful;
		this.channelId = channelId;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
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
		DABChannel other = (DABChannel) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABChannelResult [successful=" + successful + ", channelId=" + channelId + "]";
	}

}

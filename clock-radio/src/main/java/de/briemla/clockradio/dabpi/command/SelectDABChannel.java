package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABChannel;

public class SelectDABChannel extends BaseCommand<DABChannel> {

	private final Integer channelId;

	public SelectDABChannel(Integer channelId) {
		super("i");
		this.channelId = channelId;
	}

	@Override
	public DABChannel parse(Output output) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
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
		SelectDABChannel other = (SelectDABChannel) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectDABChannel [channelId=" + channelId + "]";
	}

}

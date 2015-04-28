package de.briemla.clockradio.dabpi;

import java.io.IOException;

import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABService;

public class DABStation implements IDABStation {

	private final Region region;
	private final DABService service;
	private final DABChannel channel;

	public DABStation(Region region, DABService service, DABChannel channel) {
		this.region = region;
		this.service = service;
		this.channel = channel;
	}

	@Override
	public void tuneTo(RadioController controller) throws IOException {
		controller.switchToDAB();
		controller.selectDABRegion(region);
		controller.selectDABChannel(channel);
		controller.startDABService(service);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DABStation other = (DABStation) obj;
		if (channel == null) {
			if (other.channel != null) {
				return false;
			}
		} else if (!channel.equals(other.channel)) {
			return false;
		}
		if (region != other.region) {
			return false;
		}
		if (service == null) {
			if (other.service != null) {
				return false;
			}
		} else if (!service.equals(other.service)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return service.toString();
	}

}

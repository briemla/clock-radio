package de.briemla.clockradio.dabpi.command;

import java.util.Optional;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABService;

public class StartDABService extends BaseCommand<DABService> {

	private final Integer serviceNumber;
	private final String serviceName;

	public StartDABService(Integer serviceNumber, String serviceName) {
		super("f");
		this.serviceNumber = serviceNumber;
		this.serviceName = serviceName;
	}

	@Override
	public String serialize() {
		return super.serialize() + " " + serviceNumber;
	}

	@Override
	protected DABService parseSpecialized(Output output) {
		Optional<String> startedService = output.standardAsStream().filter(line -> line.startsWith("Starting service"))
				.map(line -> line.split(" ")[2]).findFirst();
		check(startedService);
		return new DABService(serviceNumber, serviceName);
	}

	private void check(Optional<String> startedService) {
		if (!startedService.isPresent()) {
			return;
		}
		String service = startedService.get();
		if (!serviceName.equals(service)) {
			throw new IllegalArgumentException("Wrong service started: " + service + " expected: " + serviceName);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((serviceNumber == null) ? 0 : serviceNumber.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
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
		StartDABService other = (StartDABService) obj;
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
		return "StartDABService [serviceNumber=" + serviceNumber + ", serviceName=" + serviceName + "]";
	}

}

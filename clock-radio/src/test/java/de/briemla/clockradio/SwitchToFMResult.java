package de.briemla.clockradio;

public class SwitchToFMResult implements RadioResult {

	private final boolean successful;

	public SwitchToFMResult(boolean successful) {
		this.successful = successful;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

}

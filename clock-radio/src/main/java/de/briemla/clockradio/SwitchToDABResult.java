package de.briemla.clockradio;


public class SwitchToDABResult implements RadioResult {

	private final boolean successful;

	public SwitchToDABResult(boolean successful) {
		this.successful = successful;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

}

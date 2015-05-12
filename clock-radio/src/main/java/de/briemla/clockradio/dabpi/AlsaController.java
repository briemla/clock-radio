package de.briemla.clockradio.dabpi;

import java.io.IOException;

public class AlsaController {

	private static final String DEVICE = "hw:1,0";
	private static final String DEVICE_PARAMETER = "-C";
	private static final String ALSALOOP = "alsaloop";
	private final ProcessBuilder processBuilder;
	private Process audio;

	public AlsaController() {
		processBuilder = new ProcessBuilder(ALSALOOP, DEVICE_PARAMETER, DEVICE);
	}

	/**
	 * Redirect audio from dabpi capture interface to default output interface.
	 */
	public void play() {
		stop();
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void start() throws IOException {
		audio = processBuilder.start();
	}

	/**
	 * Kill alsa redirect process.
	 */
	public void stop() {
		if (audio == null) {
			return;
		}
		audio.destroyForcibly();
	}

}

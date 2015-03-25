package de.briemla.clockradio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public enum DAB_CONTROL {
	SCAN_UP("-l up", ""), FM_STATUS("-d", "Frequency");

	private static final String DABPI_CTL = "sudo /home/pi/dabpi_ctl/dabpi_ctl";
	private final String command;
	private final String prefix;

	private DAB_CONTROL(String command, String prefix) {
		this.command = command;
		this.prefix = prefix;
	}

	public Output execute() {
		Output output = new Output();
		try {
			Process process = Runtime.getRuntime().exec(DABPI_CTL + command);
			BufferedInputStream processOutput = new BufferedInputStream(process.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processOutput));
			process.waitFor();
			bufferedReader.lines().filter(line -> line.startsWith(prefix)).forEach(line -> output.addStandard(line));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
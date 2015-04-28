package de.briemla.clockradio.dabpi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DabpiExecutor implements RadioExecutor {

	private static final String DABPI_CTL = "sudo /home/pi/dabpi_ctl/dabpi_ctl";

	/**
	 * Executes the given {@link Command} and returns the parsed {@link RadioResult} matching the
	 * correct type specified by the {@link Command}.
	 *
	 * @param command
	 *            command to execute
	 * @return instance of implemented {@link RadioResult} specified by given command
	 * @throws IOException
	 *             when underlying application outputs errors
	 */
	@Override
	public <T> T execute(Command<T> command) throws IOException {
		Output output = new Output();
		try {
			Process process = Runtime.getRuntime().exec(DABPI_CTL + command.serialize());
			try (BufferedReader standardOutput = createReader(process.getInputStream());
					BufferedReader errorOutput = createReader(process.getErrorStream())) {
				process.waitFor();
				standardOutput.lines().forEach(line -> output.addStandard(line));
				errorOutput.lines().forEach(line -> output.addError(line));
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return command.parse(output);
	}

	/**
	 * Utility function to compose {@link BufferedReader} for given {@link InputStream}
	 *
	 * @param input
	 *
	 * @return given {@link InputStream} as {@link BufferedReader}
	 */
	private static BufferedReader createReader(InputStream input) {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
		return new BufferedReader(new InputStreamReader(bufferedInputStream));
	}

}

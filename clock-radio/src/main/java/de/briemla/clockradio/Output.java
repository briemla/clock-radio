package de.briemla.clockradio;

import java.util.ArrayList;
import java.util.Optional;

public class Output {

	private final ArrayList<String> standard;
	private final ArrayList<String> error;

	public Output() {
		standard = new ArrayList<>();
		error = new ArrayList<>();
	}

	public void addStandard(String line) {
		standard.add(line);
	}

	public void addError(String line) {
		error.add(line);
	}

	public String asString() {
		Optional<String> findFirst = standard.stream().findFirst();
		if (findFirst.isPresent()) {
			return findFirst.get();
		}
		return "";
	}

	/**
	 * Checks if there has something be written on the error output. Empty lines will be ignored.
	 *
	 * @return
	 */
	public boolean isErrorEmpty() {
		return error.stream().filter(text -> !text.isEmpty()).count() == 0;
	}

}

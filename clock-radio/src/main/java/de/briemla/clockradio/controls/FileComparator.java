package de.briemla.clockradio.controls;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {

	private static final int FIRST_IS_LOWER = -1;
	private static final int SECOND_IS_LOWER = 1;

	@Override
	public int compare(File first, File second) {
		if (first.isDirectory()) {
			return second.isDirectory() ? compareNames(first.getName(), second.getName()) : FIRST_IS_LOWER;
		}

		return second.isDirectory() ? SECOND_IS_LOWER : compareNames(first.getName(), second.getName());
	}

	private static int compareNames(String first, String second) {
		return first.toLowerCase().compareTo(second.toLowerCase());
	}
}
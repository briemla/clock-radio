package de.briemla.clockradio;

import java.util.AbstractList;

public class StationList {

	private AbstractList<String> stationList;

	public void add(String line) {
		stationList.add(line);
	}

	@Override
	public String toString() {
		return "StationList [stationList=" + stationList + "]";
	}

}

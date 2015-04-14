package de.briemla.clockradio.dabpi.command;

public enum Region {

	BADEN_WUERTEMBERG(0), BAYERN(1), BERLIN_BRANDENBURG(2), BREMEN(3), HAMBURG(4), HESSEN(5), MECKLENBURG_VORPOMMERN(6), NIEDERSACHSEN(
			7), NORDRHEIN_WESTFALEN(8), RHEINLAND_PFALZ(9), SAARLAND(10), SACHSEN(11), SACHSEN_ANHALT(12), SCHLESWIG_HOLSTEIN(
			13), THUERINGEN(14);

	private final Integer regionId;

	private Region(Integer regionId) {
		this.regionId = regionId;
	}

	String serialize() {
		return String.valueOf(regionId);
	}

}

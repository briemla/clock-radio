package de.briemla.clockradio;

import java.util.ArrayList;

/**
 * Controls dabpi player
 *
 * <pre>
 * ./dabpi_ctl
 * usage: ./dabpi_ctl [-a|-b]
 *   -a             init DAB mode
 *   -b             init fm mode
 *   -c frequency   tune frequency in FM mode
 *   -d             fm status
 *   -e             dab status
 *   -f service     start service of dab service list
 *   -g             get dab service list
 *   -i channel     tune to channel in dab frequency list
 *   -j region      set frequency list
 *                     0   Baden-Wuertemberg
 *                     1   Bayern
 *                     2   Berlin-Brandenburg
 *                     3   Bremen
 *                     4   Hamburg
 *                     5   Hessen
 *                     6   Mecklenburg-Vorpommern
 *                     7   Niedersachsen
 *                     8   Nordrhein-Westfalen
 *                     9   Rheinland-Pfalz
 *                     10  Saarland
 *                     11  Sachsen
 *                     12  Sachsen-Anhalt
 *                     13  Schleswig-Holstein
 *                     14  Thueringen
 *   -k region      scan frequency list
 *   -l up|down     fm seek next station
 *   -m             fm rds status
 *   -n             dab get audio info
 *   -o             dab get subchannel info
 *   -h             this help
 * </pre>
 *
 * @author Lars
 *
 */
public class RadioPlayer {

	public ArrayList<Integer> scan() {
		Integer startFrequency = currentFrequency();
		Integer lastFrequency = startFrequency;
		boolean search = true;
		boolean overflow = false;
		ArrayList<Integer> frequency = new ArrayList<>();
		while (search) {
			DAB_CONTROL.SCAN_UP.execute();
			Integer currentFrequency = currentFrequency();
			frequency.add(currentFrequency);
			overflow |= lastFrequency != currentFrequency;
			search = !startFrequency.equals(currentFrequency) && !(overflow && currentFrequency > startFrequency)
			        && !(currentFrequency.equals(Integer.MIN_VALUE));
			lastFrequency = currentFrequency;
		}
		return frequency;

	}

	private static Integer currentFrequency() {
		String status = DAB_CONTROL.FM_STATUS.execute().asString();
		if (status.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		String string = status.split(" ")[0];
		String frequency = string.substring(0, string.length() - 3);
		return Integer.parseInt(frequency);
	}

}

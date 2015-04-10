package de.briemla.clockradio.dabpi;

import java.io.IOException;

import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

/**
 * Abstraction for controlling radio.
 *
 * @author Lars
 *
 */
public interface RadioController {

	/**
	 * Switch radio to DAB mode.
	 *
	 * @return
	 * @throws IOException
	 */
	Void switchToDAB() throws IOException;

	/**
	 * Switch radio to FM mode.
	 *
	 * @return
	 * @throws IOException
	 */
	Void switchToFM() throws IOException;

	/**
	 * Tunes to given frequency and returns the tuned frequency
	 *
	 * @param frequency
	 *            to tune to
	 * @return tuned frequency
	 * @throws IOException
	 */
	TuneToFrequencyResult tuneTo(Integer frequency) throws IOException;

	/**
	 * Read current FM status and return it.
	 *
	 * @return current FM status
	 * @throws IOException
	 */
	FMStatus fmStatus() throws IOException;

	/**
	 * Read current DAB status and return it.
	 *
	 * @return current DAB status
	 * @throws IOException
	 */
	DABStatus dabStatus() throws IOException;

	/**
	 * Start given DAB service and return the started service.
	 *
	 * @param service
	 *            to start
	 * @return started service
	 * @throws IOException
	 */
	DABService startDABService(DABService service) throws IOException;

	/**
	 * Read DAB service list and return it.
	 *
	 * @return list containing all available services
	 * @throws IOException
	 */
	DABServiceList readDABServiceList() throws IOException;

	/**
	 * Select given channel and return it.
	 *
	 * @param channel
	 *            to select
	 * @return selected channel
	 * @throws IOException
	 */
	DABChannel selectDABChannel(DABChannel channel) throws IOException;

	/**
	 * Select the given DAB region.
	 *
	 * @param region
	 *            to select
	 * @return
	 * @throws IOException
	 */
	Void selectDABRegion(Region region) throws IOException;

	/**
	 * Read frequency list of given region and return it.
	 *
	 * @param region
	 *            to read frequency list for
	 * @return list containing all available frequencies for given region
	 * @throws IOException
	 */
	DABChannelList readFrequencyListFor(Region region) throws IOException;

	/**
	 * Scan to next station. This will change frequency depending on given the {@link ScanDirection}
	 * and will search for the next available frequency. The scan stops, when a frequency is found
	 * or the original frequency is reached again.
	 *
	 * If the scan reaches the lower UKW bound at 87.5MHz while scanning down, the scan will proceed
	 * with the upper bound at 108MHz and vice versa.
	 *
	 * @param direction
	 * @return
	 * @throws IOException
	 */
	Void scanNextStation(ScanDirection direction) throws IOException;

}
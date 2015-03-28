package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.dabpi.result.SwitchToDABResult;
import de.briemla.clockradio.dabpi.result.SwitchToFMResult;


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
	 * @return <code>true</code> if switch was successful, <code>false</code> otherwise
	 */
	SwitchToDABResult switchToDAB();

	/**
	 * Switch radio to FM mode.
	 *
	 * @return <code>true</code> if switch was successful, <code>false</code> otherwise
	 */
	SwitchToFMResult switchToFM();

}
package de.briemla.clockradio.dabpi;

import java.io.IOException;

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
	 * @throws IOException
	 */
	Void switchToDAB() throws IOException;

	/**
	 * Switch radio to FM mode.
	 *
	 * @return <code>true</code> if switch was successful, <code>false</code> otherwise
	 * @throws IOException
	 */
	Void switchToFM() throws IOException;

}
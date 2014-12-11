package de.briemla.clockradio;

import java.io.File;

public interface Media {

	void play(Player player);

	File getSource();
}

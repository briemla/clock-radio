package de.briemla.clockradio;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public interface OutputFactory {

    Writer create(File storageFile) throws IOException;

}

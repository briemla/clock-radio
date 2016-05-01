package de.briemla.clockradio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public interface PrintStreamFactory {

    PrintStream create(File storageFile) throws IOException;

}

package de.briemla.clockradio.controls;

import java.io.File;
import java.io.FileFilter;

public class SupportedFileFilter implements FileFilter {

    private static final String MP3 = ".mp3";

    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || isMP3(pathname);
    }

    private static boolean isMP3(File pathname) {
        return pathname.getName().toLowerCase().endsWith(MP3);
    }

}
